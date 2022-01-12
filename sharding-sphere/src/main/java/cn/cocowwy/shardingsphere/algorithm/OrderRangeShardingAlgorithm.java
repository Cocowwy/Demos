package cn.cocowwy.shardingsphere.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cocowwy.cn
 * @create 2022-01-01-17:50
 */
@Slf4j
@Component
public class OrderRangeShardingAlgorithm implements RangeShardingAlgorithm<Timestamp> {
    private static final String DEFAULT_PREFIX = "0";

    /**
     * 基于订单表的分表策略（年份后两位+月份两位作为表后缀）
     *   ps: 这里是针对between的处理
     *        - 根据between的条件找出需要路由的是哪几张表
     *          -如 between 202201~202202  --> 2201,2202
     *          -再从所有的表中（截取后缀） 得到 2201,2202,2203
     *          -遍历现有表后缀，存在between区间的就是需要路由的表
     *          -sharding 对多表好像是有优化的策略的
     *
     * @param collection 表集合，order_2201,order_2202,order_2203
     * @param rangeShardingValue preciseShardingValue between
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Timestamp> rangeShardingValue) {
        // between low and  up
        LocalDateTime low = rangeShardingValue.getValueRange().lowerEndpoint().toLocalDateTime();
        LocalDateTime up = rangeShardingValue.getValueRange().upperEndpoint().toLocalDateTime();

        // 范围查询返回的是可能存在的几张表
        String lowMonth = fillInDouble(low.getMonth().getValue());
        String lowYear = String.valueOf(low.getYear()).substring(2);
        Integer lowSufix = Integer.valueOf(lowYear + lowMonth);

        String upMonth = fillInDouble(up.getMonth().getValue());
        String upLowYear = String.valueOf(up.getYear()).substring(2);
        Integer upLowSufix = Integer.valueOf(upLowYear + upMonth);

        final String logicTableNamePrefix = rangeShardingValue.getLogicTableName() + "_";

        Set<String> rangeTables = collection.stream().filter(it -> {
            Integer sufix = Integer.valueOf(it.replace(logicTableNamePrefix, ""));
            return judgeIsBetween(sufix, lowSufix, upLowSufix);
        }).collect(Collectors.toSet());

        // 为空则全表，不过并不建议这么做
        if(CollectionUtils.isEmpty(rangeTables)){
            return collection;
        }

        return rangeTables;
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

    /**
     * 判断是否在两数中间
     * @param low
     * @param up
     * @return
     */
    private Boolean judgeIsBetween(Integer sufix, Integer low, Integer up) {
        return (low <= sufix && sufix <= up) || (up <= sufix && sufix <= low) ? Boolean.TRUE : Boolean.FALSE;
    }
}
