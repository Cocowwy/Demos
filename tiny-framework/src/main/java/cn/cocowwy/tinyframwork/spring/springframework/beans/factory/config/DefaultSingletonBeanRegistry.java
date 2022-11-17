package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
