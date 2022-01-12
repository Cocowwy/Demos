package cn.cocowwy.shardingsphere;


import cn.cocowwy.shardingsphere.entities.Order;
import cn.cocowwy.shardingsphere.repository.OrderRepositorie;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 用来测试
 *
 * @author Cocowwy
 * @create 2022-01-01-12:43
 */
@Component
@Log4j2
public class Runnnnnner implements ApplicationRunner {
    @Autowired
    private List<DataSource> dataSource;

    @Autowired
    private OrderRepositorie orderRepositorie;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void run(ApplicationArguments args) throws JsonProcessingException {
        log.info("dataSource size : {}", dataSource.size());
        for (DataSource source : dataSource) {
            log.info("ShardingDataSource databaseType : {}", ((ShardingDataSource) source).getDatabaseType());
            log.info("ShardingDataSource dataSourceMap : {}", ((ShardingDataSource) source).getDataSourceMap().keySet());
        }
        // 模拟单月
        Order order1 = new Order();
        order1.setNumber(RandomUtil.randomNumbers(5));
        order1.setCreateTime(LocalDateTime.now());
        Order order2 = new Order();
        order2.setNumber(RandomUtil.randomNumbers(5));
        order2.setCreateTime(LocalDateTime.now());

        // 模拟多1/2/3月份订单,模拟插表会按日期走不同的表
        Order order3 = new Order();
        order3.setNumber(RandomUtil.randomNumbers(5));
        order3.setCreateTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 0, ChronoUnit.MONTHS));
        Order order4 = new Order();
        order4.setNumber(RandomUtil.randomNumbers(5));
        order4.setCreateTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.MONTHS));
        Order order5 = new Order();
        order5.setNumber(RandomUtil.randomNumbers(5));
        order5.setCreateTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 2, ChronoUnit.MONTHS));

        orderRepositorie.save(order1);
        orderRepositorie.save(order2);
        orderRepositorie.save(order3);
        orderRepositorie.save(order4);
        orderRepositorie.save(order5);

        // 测试between方法 2201~2202
        List<Order> orders = orderRepositorie.queryAllByCreateTimeBetween(
                LocalDateTimeUtil.offset(LocalDateTime.now(), -5, ChronoUnit.HOURS),
                LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.MONTHS));
        System.out.println(LocalDateTimeUtil.offset(LocalDateTime.now(), -5, ChronoUnit.HOURS));
        System.out.println(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.MONTHS));
        orders.stream().forEach(System.out::println);

    }
}
