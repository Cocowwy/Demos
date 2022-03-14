package cn.cocowwy.beautifulcode.chain;

import cn.cocowwy.beautifulcode.chain.command.CheckCouponCommand;
import cn.cocowwy.beautifulcode.chain.command.CheckOrderNumberCommand;
import cn.cocowwy.beautifulcode.chain.command.CheckOrderSkuCommand;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * @author cocowwy.cn
 * @create 2022-03-03-17:47
 */
@Service
public class OrderChain extends ChainBase {
    /**
     * 三个过滤操作
     */
    @Autowired
    private CheckCouponCommand checkCouponCommand;
    @Autowired
    private CheckOrderNumberCommand checkOrderNumberCommand;
    @Autowired
    private CheckOrderSkuCommand checkOrderSkuCommand;

    @PostConstruct
    public void init() {
        this.addCommand(checkOrderNumberCommand);
        this.addCommand(checkCouponCommand);
        this.addCommand(checkOrderSkuCommand);
    }

    public OrderContext work(OrderContext context) throws Exception {
        super.execute(context);
        return context;
    }
}
