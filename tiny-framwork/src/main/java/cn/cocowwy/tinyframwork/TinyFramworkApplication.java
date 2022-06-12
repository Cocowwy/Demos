package cn.cocowwy.tinyframwork;

import cn.cocowwy.tinyframwork.spring.springframework.BeanDefinition;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.service.UserService;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinyFramworkApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TinyFramworkApplication.class, args);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.doSth();
    }
}

