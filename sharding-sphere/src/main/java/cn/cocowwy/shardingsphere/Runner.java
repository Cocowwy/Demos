package cn.cocowwy.shardingsphere;


import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-01-01-12:43
 */
@Component
@Log4j2
public class Runner implements ApplicationRunner {
    @Autowired
    private List<DataSource> dataSource;

    public void run(ApplicationArguments args) throws Exception {
        log.info("dataSource size : {}", dataSource.size());
        for (DataSource source : dataSource) {
            log.info("ShardingDataSource databaseType : {}", ((ShardingDataSource) source).getDatabaseType());
            log.info("ShardingDataSource dataSourceMap : {}", ((ShardingDataSource) source).getDataSourceMap().keySet());
        }
    }
}
