package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.support;

import cn.cocowwy.tinyframwork.spring.springframework.core.io.Resource;
import cn.cocowwy.tinyframwork.spring.springframework.core.io.ResourceLoader;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);
}
