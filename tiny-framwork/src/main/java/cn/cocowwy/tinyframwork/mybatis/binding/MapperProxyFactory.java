package cn.cocowwy.tinyframwork.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 代理类工厂，封装代理类的生成
 * @author cocowwy.cn
 * @create 2022-06-06-12:35
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
