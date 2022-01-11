//package cn.cocowwy.shardingsphere.algorithm;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * 自定义精确分片算法  分库算法
// * @author Cocowwy
// * @create 2022-01-01-21:13
// */
//@Slf4j
//@Component
//public class DefaultPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @SneakyThrows
//    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
//        log.info("availableTargetNames:" + objectMapper.writeValueAsString(collection)
//                + ",shardingValues:" + objectMapper.writeValueAsString(preciseShardingValue));
//        return null;
//    }
//}
