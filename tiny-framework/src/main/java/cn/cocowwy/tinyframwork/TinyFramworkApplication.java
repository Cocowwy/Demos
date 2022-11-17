package cn.cocowwy.tinyframwork;

import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.cocowwy.tinyframwork.spring.springframework.core.io.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TinyFramworkApplication {

    public static void main(String[] args) {
////        SpringApplication.run(TinyFramworkApplication.class, args);
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
////        UserService u1 = (UserService)beanFactory.getBean("userService");
//        UserService u2 = (UserService) beanFactory.getBean("userService", "Cocowwy");
////         u1.doSth();
//        u2.doSth();

//        // 初始化bean工厂
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // UserDao 注册
//        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
//
//        // serService 设置属性[uId、userDao]
//        PropertyValues propertyValues = new PropertyValues();
//        propertyValues.addPropertyValue(new PropertyValue("uId", "1"));
//        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
//
//        // UserService 注入 bean
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
//
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();

        // 初始化bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}


class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1", "Cocowwy");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}

class UserService {

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    // ...get/set
}