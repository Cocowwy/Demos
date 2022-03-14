package cn.cocowwy.beautifulcode.chain.command;

import cn.cocowwy.beautifulcode.chain.OrderContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 操作：
 * 如果存在sku数量为空的商品，则过滤掉
 *
 * @author cocowwy.cn
 * @create 2022-03-03-18:00
 */
@Service
public class CheckOrderSkuCommand implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        OrderContext order = (OrderContext) context;
        Map<String, Integer> skus = order.getSkus();
        Set<String> rmKeys = skus.entrySet().stream().filter(it -> it.getValue() == null || it.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        rmKeys.forEach(it -> {
            skus.remove(it);
        });
        return false;
    }
}
