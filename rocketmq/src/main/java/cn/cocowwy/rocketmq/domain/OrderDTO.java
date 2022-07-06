package cn.cocowwy.rocketmq.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class OrderDTO {
    private String orderNo;

    private LocalDateTime createTime;

    private String status;

    private BigDecimal realPrice;

    private BigDecimal realPay;
}
