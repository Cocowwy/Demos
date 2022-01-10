//package cn.cocowwy.shardingsphere.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * @author Cocowwy
// * @create 2022-01-01-12:41
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Primary
//    @Bean(name = "ds1")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds1")
//    public DataSource dataSource0() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "ds2")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds2")
//    public DataSource dataSource1() {
//        return DataSourceBuilder.create().build();
//    }
//}
