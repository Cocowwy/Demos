package cn.cocowwy.tinyframwork.mybatis.binding;

import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

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

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(null, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
