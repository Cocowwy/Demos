package cn.cocowwy.spring.transaction;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.function.Supplier;

/**
 * 保证异步操作在事务提交之后进行，实现该接口 调用afterCommitDo即可
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface AbsTransactionalCommitModel<T> {
    default void afterCommitDo(Supplier<T> afterCommitDo) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                afterCommitDo.get();
            }
        });
    }
}