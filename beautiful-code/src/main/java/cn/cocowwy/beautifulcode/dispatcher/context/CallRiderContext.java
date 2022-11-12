package cn.cocowwy.beautifulcode.dispatcher.context;

import lombok.Data;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class CallRiderContext extends BaseDeliveryContext {

    private String name;

    private String mobile;

    private String lng;

    private String lat;

    private String address;
}
