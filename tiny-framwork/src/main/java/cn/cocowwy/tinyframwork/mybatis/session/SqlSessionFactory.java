package cn.cocowwy.tinyframwork.mybatis.session;

/**
 * 通过工厂模式获取SqlSession
 * @author cocowwy.cn
 * @create 2022-06-06-18:35
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
