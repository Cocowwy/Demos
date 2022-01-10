package cn.cocowwy.shardingsphere.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:18
 */
@Data
@Table("user")
public class User {
    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;
}
