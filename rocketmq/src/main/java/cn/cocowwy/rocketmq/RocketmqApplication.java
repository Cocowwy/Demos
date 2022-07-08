package cn.cocowwy.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * docker pull apache/rocketmq
 * docker run -it --net=host -d apache/rocketmq ./mqnamesrv
 * docker run -it --net=host -d apache/rocketmq ./mqbroker -n localhost:9876
 *
 *  修改RocketMQ 内存大小
 *  https://blog.csdn.net/u012069313/article/details/124839562?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-124839562-blog-114257774.pc_relevant_aa2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-124839562-blog-114257774.pc_relevant_aa2&utm_relevant_index=2
 *  cd /usr/local/cocowwy/rocketmq-4.9.3/bin/
 *  nohup sh mqnamesrv  -n "xxxx:9876" &
 *  nohup sh mqbroker -n localhost:9876 -c ../conf/broker.conf autoCreateTopicEnable=true &
 */
@SpringBootApplication
public class RocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

}


