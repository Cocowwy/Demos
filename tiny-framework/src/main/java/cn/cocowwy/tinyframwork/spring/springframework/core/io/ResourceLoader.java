package cn.cocowwy.tinyframwork.spring.springframework.core.io;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}