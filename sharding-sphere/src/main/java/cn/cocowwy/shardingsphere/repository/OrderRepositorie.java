package cn.cocowwy.shardingsphere.repository;

import cn.cocowwy.shardingsphere.entities.Order;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:20
 */
public interface OrderRepositorie extends CrudRepository<Order, Long> {
    Order queryAllBy(Long id);

    // !! 这里如果不用Query 使用方法名的方式进行查询 好像并不会很兼容（有空debug下源码，反正是拼接的SQL有问题）
    // 所以建议SQL写活
    @Query("select * from order where create_time between :start and :end")
    List<Order> queryAllByCreateTimeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
