package cn.cocowwy.beautifulcode;

import cn.cocowwy.beautifulcode.chain.OrderChain;
import cn.cocowwy.beautifulcode.chain.OrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * chain  -  使用责任链模式来进行解耦
 *  1. 构造一个简单的订单对象，通过责任链模式来解耦操作
 *  2. 责任链中模拟了三个校驗操作
 *  3. 执行见 {@link cn.cocowwy.beautifulcode.chain.OrderChain}
 *           {@link cn.cocowwy.beautifulcode.chain.command.CheckCouponCommand} 优惠券校验
 *           {@link cn.cocowwy.beautifulcode.chain.command.CheckOrderNumberCommand} 订单号校验
 *           {@link cn.cocowwy.beautifulcode.chain.command.CheckOrderSkuCommand} sku校验
 *  4. command 在返回值上 如果是return的true 则不执行后面的过滤链逻辑，所以如果要都走通，则应 return false
 *
 * @author cocowwy.cn
 * @create 2022-03-03-17:36
 */
@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private OrderChain orderChain;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 校验订单号不通过/优惠券不通过
//        OrderContext od = new OrderContext();
//        od.setNumber("100001");
//        od.setCouponIds(Set.of(1L));
//        orderChain.work(od);

        // 清洗数据
        OrderContext od = new OrderContext();
        od.setNumber("100002");
        od.setCouponIds(Set.of(2L));
        Map<String, Integer> skus = od.getSkus();
        skus.put("A01", 1);
        skus.put("A02", 2);
        // 过滤后 A03被移除
        skus.put("A03", 0);
        OrderContext work = orderChain.work(od);
        System.out.println("过滤后商品为：" + Arrays.toString(work.getSkus().keySet().toArray()));
    }
}
