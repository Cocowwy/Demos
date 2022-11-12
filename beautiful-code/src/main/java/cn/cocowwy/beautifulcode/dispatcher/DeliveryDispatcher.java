package cn.cocowwy.beautifulcode.dispatcher;

import cn.cocowwy.beautifulcode.dispatcher.context.CallRiderContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Component
public class DeliveryDispatcher implements InitializingBean, ApplicationContextAware {
    private ApplicationContext appContext;
    private final Map<String, Deliverable> candidates = new HashMap<>();

    /**
     * 分派订单
     * @param orderNo 订单号
     */
    public void dispatcherOrder(String orderNo, String platform) {
        CallRiderContext context = new CallRiderContext();
        context.setOrderNo(orderNo);
        candidates.get(platform).callRider(context);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Deliverable> map = appContext.getBeansOfType(Deliverable.class);
        if (!CollectionUtils.isEmpty(map)) {
            map.values().forEach(it -> candidates.put(it.getPlatform(), it));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
