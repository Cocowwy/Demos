package cn.cocowwy.tinyframwork.mybatis.session;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL 信息记录对象 包括记录 SQL类型 语句 入参类型 出参类型
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry();

    /**
     * 映射的语句
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }
}
