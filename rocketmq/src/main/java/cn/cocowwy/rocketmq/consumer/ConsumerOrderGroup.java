package cn.cocowwy.rocketmq.consumer;

import cn.cocowwy.rocketmq.domain.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
public class ConsumerOrderGroup {

    @Service
    @RocketMQMessageListener(topic = "TEST", consumerGroup = "pay-group")
    public class ConsumerOrder4Pay implements RocketMQListener<OrderDTO> {
        @Override
        public void onMessage(OrderDTO orderDTO) {
            log.info("监听订单支付 ----> {}", orderDTO);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "TEST", consumerGroup = "order-status-group")
    public class Consumer1OrderStatus implements RocketMQListener<OrderDTO> {
        @Override
        public void onMessage(OrderDTO orderDTO) {
            log.info("监听订单状态变更 ----> {}", orderDTO);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "TEST", consumerGroup = "order-status-group")
    public class Consumer2OrderStatus implements RocketMQListener<OrderDTO> {
        @Override
        public void onMessage(OrderDTO orderDTO) {
            log.info("监听订单状态变更 ----> {}", orderDTO);
        }
    }
}
