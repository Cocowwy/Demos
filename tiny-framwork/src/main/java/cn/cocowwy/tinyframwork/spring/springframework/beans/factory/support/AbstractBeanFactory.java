package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support;

import cn.cocowwy.tinyframwork.spring.springframework.BeanDefinition;
import cn.cocowwy.tinyframwork.spring.springframework.BeanFactory;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.config.DefaultSingletonBeanRegistry;

/**
 * 抽象bean工厂
 *
 * BeanFactory 定义了 getBean的方法
 * DefaultSingletonBeanRegistry 继承了单例池以及获取注册bean的方法
 * AbstractBeanFactory 结合了上述，提供了bean的定义信息，以及bean的创建的接口 以及提供了getBean的基本实现
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

}
