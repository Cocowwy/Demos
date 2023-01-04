package cn.cocowwy.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Spring 的 PlatformTransactionManager Demo
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class PlatformTransactionManagerDemo {
    @Autowired(required = false)
    private PlatformTransactionManager txManager;

    void springPlatformTransactionManager() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can be done only programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            // put your business logic here
        } catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);
    }
}
