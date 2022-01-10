package cn.cocowwy.shardingsphere.repositorie;

import cn.cocowwy.shardingsphere.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:19
 */
public interface UserRepositorie extends CrudRepository<Long, User> {
}
