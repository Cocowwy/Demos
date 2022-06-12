package cn.cocowwy.tinyframwork.spring.springframework;


/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface BeanFactory {
    Object getBean(String name);

    Object getBean(String name, Object... args);
}
