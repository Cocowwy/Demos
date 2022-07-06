package cn.cocowwy.rocketmq.producer;

import cn.cocowwy.rocketmq.domain.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * 订单消息 生产者
 * https://github.com/apache/rocketmq-spring/wiki/Send-Message
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
@Slf4j
public class ProducerOrder implements CommandLineRunner {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {
        // mock create
        EasyRandom easyRandom = new EasyRandom();
        OrderDTO orderDTO = easyRandom.nextObject(OrderDTO.class);

        //send spring message
        log.info("send mq msg: {}", orderDTO);
        rocketMQTemplate.send("order-message", MessageBuilder.withPayload(orderDTO).build());
    }
}
