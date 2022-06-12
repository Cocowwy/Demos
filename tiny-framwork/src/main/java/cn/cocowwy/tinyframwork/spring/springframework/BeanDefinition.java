package cn.cocowwy.tinyframwork.spring.springframework;

/**
 * Bean 的 定义信息
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class BeanDefinition {
    private final Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
