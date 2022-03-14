package cn.cocowwy.beautifulcode.chain.command;

import cn.cocowwy.beautifulcode.chain.OrderContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

/**
 * 操作：
 * 如果订单优惠含有id = 1的优惠券 则禁止下单
 *
 * @author cocowwy.cn
 * @create 2022-03-03-17:59
 */
@Service
public class CheckCouponCommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        OrderContext order = (OrderContext) context;
        if (order.getCouponIds().contains(1L)) {
            throw new Exception("订单优惠含有id = 1的优惠券 则禁止下单");
        }
        return false;
    }
}
