package cn.cocowwy.tinyframwork.mybatis.binding;

import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-18:00
 */
public class MapperRegistry {
    /**
     * 所有的 Mapper 代理类的注册中心
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (Objects.isNull(mapperProxyFactory)) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        } else {
            try {
                return mapperProxyFactory.newInstance(sqlSession);
            } catch (Exception var5) {
                throw new RuntimeException("Error getting mapper instance. Cause: " + var5, var5);
            }
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return this.knownMappers.containsKey(type);
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (this.hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }

            // ignore..
            this.knownMappers.put(type, new MapperProxyFactory(type));
            // ignore..
        }
    }


    // 暂时用hutool的工具来简化扫描类的操作
    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
