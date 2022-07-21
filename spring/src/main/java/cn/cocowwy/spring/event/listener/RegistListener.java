package cn.cocowwy.spring.event.listener;

import cn.cocowwy.spring.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class RegistListener {
    @EventListener
    public void register(UserRegisterEvent event) {
        System.out.println("账户 [" + event.getUserName() + "] 注册了！");
    }
}
