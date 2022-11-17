package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.config;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}