package cn.cocowwy.shardingsphere;


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
public class Runner implements ApplicationRunner {
    @Autowired
    private List<DataSource> dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(dataSource.size());
    }
}
