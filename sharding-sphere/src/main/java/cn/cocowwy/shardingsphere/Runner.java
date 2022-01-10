package cn.cocowwy.shardingsphere;

import cn.cocowwy.shardingsphere.entities.User;
import cn.cocowwy.shardingsphere.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
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
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
