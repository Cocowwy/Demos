package cn.cocowwy.beautifulcode.dispatcher;

import cn.cocowwy.beautifulcode.dispatcher.context.BaseDeliveryContext;
import cn.cocowwy.beautifulcode.dispatcher.context.CallRiderContext;
import cn.cocowwy.beautifulcode.dispatcher.context.CancelDeliveryContext;

/**
 * 这里以对接多个第三方配送渠道作为demo
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface Deliverable {
    /**
     * 获取配送渠道
     *
     * @return 当前的配送渠道
     */
    String getPlatform();

    /**
     * 呼叫骑手
     *
     * @param context 上下文
     */
    void callRider(CallRiderContext context);

    /**
     * 获取骑手当前位置
     *
     * @param context 上下文
     * @return 骑手位置的经纬度
     */
    String getRiderLocation(BaseDeliveryContext context);

    /**
     * 取消配送订单
     */
    void cancelDelivery(CancelDeliveryContext context);
}
