package cn.cocowwy.tinyframwork.mybatis.session.defaults;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperRegistry;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-18:33
 */
public class DefaultSqlSession implements SqlSession {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public <T> T selectOne(String statement) {
        return (T) "查询一条记录";
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
