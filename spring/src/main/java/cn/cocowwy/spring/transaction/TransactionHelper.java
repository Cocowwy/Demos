package cn.cocowwy.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Objects;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/4
 */
@Component
public class TransactionHelper {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    public TransactionStatus begin(TransactionDefinition transactionDefinition) {
        return dataSourceTransactionManager.getTransaction(Objects.isNull(transactionDefinition)
                ? new DefaultTransactionAttribute() : transactionDefinition);
    }

    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    public void rollback(TransactionStatus transaction) {
        dataSourceTransactionManager.rollback(transaction);
    }

    /**
     * 使用此方法的位置必须要有 {@link Transactional} 注解
     *
     * @param runnable 事务提交后执行的行为
     */
    public static void doAfterCommit(Runnable runnable) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                runnable.run();
            }
        });
    }
}