package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.config;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}