package cn.cocowwy.shardingsphere.repository;

import cn.cocowwy.shardingsphere.entities.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:20
 */
public interface OrderRepositorie extends CrudRepository<Order, Long> {
    Order queryAllBy(Long id);
}
