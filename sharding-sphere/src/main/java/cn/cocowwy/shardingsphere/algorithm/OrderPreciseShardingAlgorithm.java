package cn.cocowwy.shardingsphere.algorithm;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 * 订单表的分表算法
 * @author Cocowwy
 * @create 2022-01-01-21:13
 */
@Slf4j
@Component
public class OrderPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Timestamp> {
    private static final String DEFAULT_PREFIX = "0";

    /**
     * 基于订单表的分表策略（年份后两位+月份两位作为表后缀）
     *   ps: -只能通过Timestamp转LocalDateTime
     *       -分片键不可为空
     *       -这里默认路由不到则给个默认的路由
     *       -如果需要订单号来查的话，强制要求查询条件里面带上创建时间（建议订单号里面带上创建时间，便于从订单号维度来定制路由逻辑）
     * @param collection 表集合，order_2201,order_2202,order_2203
     * @param preciseShardingValue preciseShardingValue 分表标识列
     * @return
     */
    @SneakyThrows
    public String doSharding(Collection<String> collection, PreciseShardingValue<Timestamp> preciseShardingValue) {
        LocalDateTime orderCreateTime = preciseShardingValue.getValue().toLocalDateTime();

        String month = fillInDouble(orderCreateTime.getMonth().getValue());
        String year = String.valueOf(orderCreateTime.getYear()).substring(2);
        String sufix = year + month;
        Optional<String> db = collection.stream().filter(it -> it.endsWith(sufix)).findFirst();

        // 如果分表策略推出来的表未在collection中有找到，则默认找第一张表
        return db.isPresent() ? db.get() : collection.stream().findFirst().get();
    }

    /**
     * 月份不足两位，补足
     * @param number
     * @return
     */
    private String fillInDouble(int number) {
        String str = String.valueOf(number);
        if (str.length() < 2) {
            str = DEFAULT_PREFIX + str;
        }
        return str;
    }
}
