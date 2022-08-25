package cn.cocowwy.beautifulcode.juc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现在事务内进行多线程inert操作，并保证事务
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class TransactionAsyncService {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private PersonService personService;

    @Transactional
    void transactionAsync() {
        CyclicBarrier cb = new CyclicBarrier(10);

        AtomicReference<Boolean> rollback = new AtomicReference<>(false);

        for (int i = 0; i < 10; i++) {
            int currentNum = i;

            new Thread(() -> {
                // 手动开启事务
                TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
                try {
                    // insert操作,如果插入数据<1则抛异常
                    if (personService.insertPerson("Cocowwy-" + currentNum) < 1) {
                        throw new RuntimeException("插入数据失败");
                    }
                    // 等待所有线程的事务结果
                    cb.await();
                    // 如果标志需要回滚，则回滚
                    if (rollback.get()) {
                        dataSourceTransactionManager.rollback(transaction);
                        return;
                    }

                    dataSourceTransactionManager.commit(transaction);
                } catch (Exception e) {
                    // 如果当前线程执行异常，则设置回滚标志
                    rollback.set(true);
                    dataSourceTransactionManager.rollback(transaction);
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}

@Service
class PersonService {
    int insertPerson(String name) {
        return 1;
    }
}