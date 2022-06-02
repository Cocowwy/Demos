package cn.cocowwy.beautifulcode.subscribe.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-17:28
 */
@Data
@ToString(callSuper = true)
public class MemberV2Dto extends MemberV1Dto {
    private LocalDate birthday;
    private String address;
}
