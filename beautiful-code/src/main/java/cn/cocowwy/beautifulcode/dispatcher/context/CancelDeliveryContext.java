package cn.cocowwy.beautifulcode.dispatcher.context;

import lombok.Data;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class CancelDeliveryContext extends BaseDeliveryContext{
    private String reason;
}
