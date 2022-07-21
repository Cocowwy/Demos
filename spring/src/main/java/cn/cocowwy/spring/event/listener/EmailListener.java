package cn.cocowwy.spring.event.listener;

import cn.cocowwy.spring.event.evt.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
@Order(1)
public class EmailListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    // 同时进行异步处理
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        sendMessageByEmail(event.getEmail(), String.format("账户：%s ， 您的验证码为：123123", event.getUserName()));
    }

    void sendMessageByEmail(String email, String content) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format("send email to [%s] content [%s] \n", email, content);
    }
}
