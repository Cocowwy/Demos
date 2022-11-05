package cn.cocowwy.spring.transaction;

import cn.cocowwy.spring.event.evt.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * spring提供的版本为：Spring 4.2
 * TransactionalEventListener
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class TransactionalEventListenerDemo {

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public void userRegister() {
        // store user info...

        // put user create message
        UserRegisterEvent userRegisterEvent = new UserRegisterEvent(this);
        applicationContext.publishEvent(userRegisterEvent);
    }


}

/**
 * 表示仅在事务提交之后再处理事件
 * 如果事务没有在运行 则不会调用侦听器，当然可以将 fallbackExecution 设置为true来覆盖该行为
 */
@Service
class TransactionalEventListenerProcessor {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = false)
    public void processUserCreatedEvent(UserRegisterEvent event) {
        // full your logic
    }
}