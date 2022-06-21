package cn.cocowwy.tinyframwork.spring.springframework;

import cn.cocowwy.tinyframwork.spring.springframework.beans.factory.beans.PropertyValues;

/**
 * Bean 的 定义信息
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class BeanDefinition {
    private final Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
