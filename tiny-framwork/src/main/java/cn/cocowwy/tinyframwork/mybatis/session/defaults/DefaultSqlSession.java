package cn.cocowwy.tinyframwork.mybatis.session.defaults;

import cn.cocowwy.tinyframwork.mybatis.session.Configuration;
import cn.cocowwy.tinyframwork.mybatis.session.SqlSession;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-18:33
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public <T> T selectOne(String statement) {
        return (T) "查询一条记录";
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
