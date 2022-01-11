package cn.cocowwy.shardingsphere;

import cn.cocowwy.shardingsphere.entities.Order;
import cn.cocowwy.shardingsphere.entities.User;
import cn.cocowwy.shardingsphere.mapper.OrderMapper;
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
    private OrderMapper orderMapper;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(("----- selectAll method test ------"));
//        List<Order> userList = orderMapper.selectList(null);
        Order order1 = new Order();
        order1.setNumber("123123");

        Order order2 = new Order();
        order2.setNumber("123124");

        Order order3 = new Order();
        order3.setNumber("123125");

        orderMapper.insert(order1);
        orderMapper.insert(order2);
        orderMapper.insert(order3);
    }
}
