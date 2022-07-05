package cn.cocowwy.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * docker pull apache/rocketmq
 * docker run -it --net=host -d apache/rocketmq ./mqnamesrv
 * docker run -it --net=host -d apache/rocketmq ./mqbroker -n localhost:9876
 */
@SpringBootApplication
public class RocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

}


