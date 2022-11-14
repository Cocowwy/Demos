package cn.cocowwy.grpcjava.heartdemo;

import cn.cocowwy.grpcjava.heartdemo.heart.HeartReq;
import cn.cocowwy.grpcjava.heartdemo.heart.HeartResp;
import cn.cocowwy.heart.HeartReportServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        /**
         * 开10个线程模拟10个服务
         */
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {

                while (true) {
                    ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9000)
                            .usePlaintext().build();
                    String serverName = "server" + finalI;
                    HeartReq req = HeartReq.newBuilder().setServerId(1).setServerName(serverName).build();

                    HeartReportServiceGrpc.HeartReportServiceBlockingStub stub = HeartReportServiceGrpc.newBlockingStub(channel);
                    HeartResp heartResp = stub.handleHeart(req);

                    if (heartResp.getRes()) {
                        log.info(serverName + "心跳上报成功");
                    }

                    try {
                        // 每5s上报一次心跳
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }).start();

        }
    }
}
