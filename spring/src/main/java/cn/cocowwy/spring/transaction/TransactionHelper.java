package cn.cocowwy.spring.transaction;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/4
 */
public class TransactionHelper {

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