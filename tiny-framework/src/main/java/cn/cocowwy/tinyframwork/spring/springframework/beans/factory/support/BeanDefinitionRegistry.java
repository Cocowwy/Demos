package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support;

import cn.cocowwy.tinyframwork.spring.springframework.BeanDefinition;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
