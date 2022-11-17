package cn.cocowwy.tinyframwork.mybatis.session;

import cn.cocowwy.tinyframwork.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.cocowwy.tinyframwork.mybatis.test.UserMapeer;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;

import java.io.Reader;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        // {@link org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration} 构造configuration
        org.apache.ibatis.session.Configuration config = xmlConfigBuilder.parse();
        // mock 这里暂时用自建的 config 来代替
        Configuration configuration = new Configuration();
        configuration.addMapper(UserMapeer.class);
        return build(configuration);
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
