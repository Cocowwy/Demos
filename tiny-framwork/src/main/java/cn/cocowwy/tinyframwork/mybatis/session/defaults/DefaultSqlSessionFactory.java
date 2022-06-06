package cn.cocowwy.tinyframwork.mybatis.session.defaults;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperRegistry;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSessionFactory;

/**
 *
 * @author cocowwy.cn
 * @create 2022-06-06-18:36
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    /**
     * 获取默认的 SqlSession ，并且将 MapperRegistry 的引用传入
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
