package cn.cocowwy.common.dto;

import lombok.Data;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class Book {
    private Long id;
    private String name;
    private boolean valid = true;
}
