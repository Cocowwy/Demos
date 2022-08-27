package cn.cocowwy.spring.event.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * test1：
 *  事物执行完之后再执行相应的操作，如订单下单后，再执行MQ发送等操作
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class TransactionService {

    @Transactional
    public void test() {

        // 一系列DB操作...
        // xxxMapper.insert(..);
        doSthWhenCommit();
    }

    public void doSthWhenCommit() {
        // 开启事物, 则在事物提交后
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    // do sth.
                    // mq.push(msg)
                }
            });
        }
    }
}
