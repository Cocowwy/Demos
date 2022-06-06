package cn.cocowwy.tinyframwork.mybatis.session;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-18:10
 */
public interface SqlSession {
    <T> T selectOne(String statement);

    <T> T getMapper(Class<T> type);
}
