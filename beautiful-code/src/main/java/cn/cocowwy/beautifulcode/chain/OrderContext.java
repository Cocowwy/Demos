package cn.cocowwy.beautifulcode.chain;

import org.apache.commons.chain.impl.ContextBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author cocowwy.cn
 * @create 2022-03-03-17:53
 */
public class OrderContext extends ContextBase {
    /**
     * 订单号
     */
    private String number;

    /**
     * 优惠券号
     */
    private Set<Long> couponIds;

    /**
     * 商品项+数量
     */
    private Map<String, Integer> skus = new HashMap<>();

    /**
     * 扩展
     */
    private Map<String, String> expand;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Long> getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(Set<Long> couponIds) {
        this.couponIds = couponIds;
    }

    public Map<String, Integer> getSkus() {
        return skus;
    }

    public void setSkus(Map<String, Integer> skus) {
        this.skus = skus;
    }
}
