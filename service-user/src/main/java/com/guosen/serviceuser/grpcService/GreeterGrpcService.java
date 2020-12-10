package com.guosen.serviceuser.grpcService;

import com.guosen.serviceuser.proto.helloworld.HelloReply;
import com.guosen.serviceuser.proto.helloworld.HelloRequest;
import com.guosen.serviceuser.proto.helloworld.GreeterGrpc.GreeterImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterGrpcService extends GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request ,StreamObserver<HelloReply> responseObserver){
        System.out.println("GrpcServerService...11111");
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
