package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support;

import cn.cocowwy.tinyframwork.spring.springframework.BeanDefinition;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.beans.PropertyValue;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.beans.PropertyValues;
import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * 提供父类createBean的基本实现
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 使用 cglib 生成对对象
     *
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    @SneakyThrows
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        // 使用 cglib 的方式来实现
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (propertyValues == null) {
                return;
            }
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
