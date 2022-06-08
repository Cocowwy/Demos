package cn.cocowwy.tinyframwork.mybatis.test;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperRegistry;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSessionFactory;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.Reader;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-12:46
 */
public class TestMain {
    public static void main(String[] args) {
        // 注册所有的 Mapper接口，并且为每个 Mapper 接口生成 MapperProxyFactory 工厂
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("cn.cocowwy.tinyframwork.mybatis.test");

        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        } catch (IOException e) {
            // mock.. ignore..
        }
//        根据maybatis的配置文件 生成 SqlSessionFactory 的配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapeer mapper = sqlSession.getMapper(UserMapeer.class);
    }
}
