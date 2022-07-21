package cn.cocowwy.spring.event;

import cn.cocowwy.spring.event.evt.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 事件通知 观察者，订阅者 模式
 *    两种监听方式，一种是 实现ApplicationListener<T>接口
 *               一种是  EventListener注解 方法使用事件作为入参
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class EventTest implements ApplicationRunner {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("发送事件");
        applicationContext.publishEvent(new UserRegisterEvent(this, "Cocowwy", "xxxx@qq.com", BigDecimal.valueOf(9999999L)));
    }
}
