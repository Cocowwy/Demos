package cn.cocowwy.grpcjava;

import cn.cocowwy.grpcserver.api.RPCDateRequest;
import cn.cocowwy.grpcserver.api.RPCDateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class GrpcClient {
    public static void main(String[] args) {
        //1.拿到一个通信的channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext().build();
        try {
            //2.将信息传入request对象
            RPCDateRequest build = RPCDateRequest.newBuilder()
                    .setId(117)
                    .setUserName("Cocowwy")
                    .setMessage("hello grpc")
                    .setAge(22).build();
            System.out.println("build:" + build + "...");
            RPCDateServiceGrpc.RPCDateServiceBlockingStub stub = RPCDateServiceGrpc.newBlockingStub(channel);
            stub.getDate(build);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if (next.equals("exit")) {
                    break;
                }
            }
        } finally {
            channel.shutdown();
        }
    }
}
