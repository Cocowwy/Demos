package cn.cocowwy.tinyframwork.mybatis.binding;

import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 实际代理 Mapper 的类，代理类的逻辑
 * @author cocowwy.cn
 * @create 2022-06-06-12:06
 */
@Slf4j
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 返回该方法的实际的类型，即不代理 Object 的方法
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        return String.format("代理Mapper接口 %s#%s#%s", mapperInterface, method.getName(), Arrays.toString(args));
    }
}
