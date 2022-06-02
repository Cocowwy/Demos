package cn.cocowwy.beautifulcode.subscribe.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-17:28
 */
@Data
@ToString(callSuper = true)
public class MemberV3Dto extends MemberV2Dto{
    private BigDecimal money;
}
