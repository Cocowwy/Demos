package cn.cocowwy.grpcjava;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class GrpcServer {
    public static void main(String[] args) {
        Server server = null;
        try {
            server = ServerBuilder.forPort(8888)
                    .addService(new RPCDateServiceImpl())
                    .build().start();
            server.awaitTermination();//持续等待信息
        } catch (IOException e) {
            System.out.println("server--错误信息===>" + e);
        } catch (InterruptedException e) {
            System.out.println("server--错误信息===>" + e);
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }
}
