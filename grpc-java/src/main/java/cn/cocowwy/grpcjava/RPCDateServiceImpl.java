package cn.cocowwy.grpcjava;

import cn.cocowwy.grpcserver.api.RPCDateRequest;
import cn.cocowwy.grpcserver.api.RPCDateResponse;
import cn.cocowwy.grpcserver.api.RPCDateServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class RPCDateServiceImpl extends RPCDateServiceGrpc.RPCDateServiceImplBase {
    @Override
    public void getDate(RPCDateRequest request, StreamObserver<RPCDateResponse> responseObserver) {
        try {
            String message = request.getId() + "\ngetUserName:" +
                    request.getUserName() + "\ngetMessage:" +
                    request.getMessage() + "\ngetAge:" +
                    request.getAge() + "\ngetSerializedSize:" +
                    request.getAllFields();
            System.out.println("server get message：" + message);
            RPCDateResponse build = RPCDateResponse.newBuilder().setServerDate(message).build();
            System.out.println("bulid:" + build);
            responseObserver.onNext(build);
            responseObserver.onCompleted();
        } catch (Exception e) {
            System.out.println("impl---错误信息====》");
            responseObserver.onError(e);
        }
    }
}
