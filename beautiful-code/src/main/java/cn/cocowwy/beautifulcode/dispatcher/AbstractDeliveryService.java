package cn.cocowwy.beautifulcode.dispatcher;

import cn.cocowwy.beautifulcode.dispatcher.context.CallRiderContext;
import cn.cocowwy.beautifulcode.dispatcher.context.CancelDeliveryContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public abstract class AbstractDeliveryService implements Deliverable {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void callRider(CallRiderContext context) {
        preCallRider(context);
        doCallRider(context);
        postCallRider(context);
    }

    /**
     * 可以进行一些落库，MQ等的操作，抽象实现后置
     */
    protected void postCallRider(CallRiderContext context) {
    }

    /**
     * 【将实际的呼叫骑手的部分交给子类实现】，即顺丰，美团，饿了么等骑手呼叫端
     */
    protected abstract void doCallRider(CallRiderContext context);

    /**
     * 可以进行一些前置的操作，抽象实现前置
     */
    protected void preCallRider(CallRiderContext context) {
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDelivery(CancelDeliveryContext context) {
        preCancel(context);
        doCancel(context);
        postCancel(context);
    }

    /**
     * 可以进行一些落库，MQ等的操作，抽象实现后置
     */
    private void postCancel(CancelDeliveryContext context) {
    }

    /**
     * 【将实际的呼叫骑手的部分交给子类实现】，即顺丰，美团，饿了么等端口取消
     */
    protected abstract void doCancel(CancelDeliveryContext context);

    /**
     * 可以进行一些前置的操作，抽象实现前置
     */
    private void preCancel(CancelDeliveryContext context) {

    }
}
