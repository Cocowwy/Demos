package cn.cocowwy.beautifulcode.dispatcher;

import cn.cocowwy.beautifulcode.dispatcher.context.BaseDeliveryContext;
import cn.cocowwy.beautifulcode.dispatcher.context.CallRiderContext;
import cn.cocowwy.beautifulcode.dispatcher.context.CancelDeliveryContext;
import org.springframework.stereotype.Service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class MeiTuanDeliveryService extends AbstractDeliveryService {
    @Override
    protected void doCallRider(CallRiderContext context) {

    }

    @Override
    protected void doCancel(CancelDeliveryContext context) {

    }

    @Override
    public String getPlatform() {
        return null;
    }

    @Override
    public String getRiderLocation(BaseDeliveryContext context) {
        return null;
    }
}
