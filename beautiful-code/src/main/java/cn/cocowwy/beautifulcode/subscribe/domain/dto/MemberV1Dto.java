package cn.cocowwy.beautifulcode.subscribe.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-17:26
 */
@Data
@ToString(callSuper = true)
public class MemberV1Dto extends MemberBaseDto {
    private String name;
}
