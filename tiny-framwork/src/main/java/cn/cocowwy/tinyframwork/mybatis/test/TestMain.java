package cn.cocowwy.tinyframwork.mybatis.test;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperRegistry;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSessionFactory;
import cn.cocowwy.tinyframwork.mybatis.session.defaults.DefaultSqlSessionFactory;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-12:46
 */
public class TestMain {
    public static void main(String[] args) {
        // 注册所有的 Mapper接口，并且为每个 Mapper 接口生成 MapperProxyFactory 工厂
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("cn.cocowwy.tinyframwork.mybatis.test");

        // new 了默认的 DefaultSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过 sqlSession 来获取 mapperRegistry 内部保存的 MapperProxyFactory --> 再获取代理对象
        UserMapeer mapper = sqlSession.getMapper(UserMapeer.class);
        String s = mapper.queryUserNameByUerId(123L);
        System.out.println(s);
    }
}
