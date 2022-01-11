package cn.cocowwy.shardingsphere.repository;

import cn.cocowwy.shardingsphere.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:19
 */
public interface UserRepositorie extends CrudRepository<User, Long> {
}
