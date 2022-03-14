package cn.cocowwy.beautifulcode.chain.command;

import cn.cocowwy.beautifulcode.chain.OrderContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

/**
 * 操作：
 * 如果订单号等于 100001 业余上认为订单号已经存在
 *
 * @author cocowwy.cn
 * @create 2022-03-03-17:59
 */
@Service
public class CheckOrderNumberCommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        OrderContext order = (OrderContext) context;
        if (order.getNumber().equals("100001")) {
            throw new Exception("订单号已经存在！");
        }
        return false;
    }
}
