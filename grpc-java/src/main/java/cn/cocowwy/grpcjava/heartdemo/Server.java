package cn.cocowwy.grpcjava.heartdemo;

import cn.cocowwy.grpcjava.heartdemo.heart.HeartReq;
import cn.cocowwy.grpcjava.heartdemo.heart.HeartResp;
import cn.cocowwy.heart.HeartReportServiceGrpc;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用grpc模拟心跳功能
 * 注册中心
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Server {
    /**
     * 服务名和心跳次数的Map
     */
    private static final Map<String, Long> serverMapHeart = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("==========> 启动服务端口 <=========");

        try {
            io.grpc.Server server = ServerBuilder.forPort(9000)
                    .addService(new HeartServiceImpl())
                    .build();
            server.start();
            // Waits for the server to become terminated.
            server.awaitTermination();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("========> 主进程退出 <========");
    }

    /**
     * 定义服务端处理 handleHeart 的方法
     * HeartReportServiceGrpc.HeartReportServiceImplBase 由 mvn 命令生成并从target目录拷贝到项目下就行
     */
    public static class HeartServiceImpl extends HeartReportServiceGrpc.HeartReportServiceImplBase {
        @Override
        public void handleHeart(HeartReq request, StreamObserver<HeartResp> responseObserver) {
            Long times = serverMapHeart.get(request.getServerName());
            if (Objects.isNull(times)) {
                serverMapHeart.put(request.getServerName(), 1L);
            } else {
                serverMapHeart.put(request.getServerName(), ++times);
            }
            // onNext 传递给流的值
            responseObserver.onNext(HeartResp.newBuilder().setRes(true).build());
            responseObserver.onCompleted();
        }
    }
};
