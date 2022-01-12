package cn.cocowwy.shardingsphere.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author Cocowwy
 * @create 2022-01-01-21:18
 */
@Data
@Table("order")
public class Order {
    @Id
    @Column("id")
    private Long id;

    @Column("number")
    private String number;

    @Column("create_time")
    private LocalDateTime createTime;
}
