package com.guosen.serviceuser.grpcService;

import com.guosen.serviceuser.proto.helloworld.HelloReply;
import com.guosen.serviceuser.proto.helloworld.HelloRequest;
import com.guosen.serviceuser.proto.helloworld.DiscoveryReply;
import com.guosen.serviceuser.proto.helloworld.GreeterGrpc.GreeterImplBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterGrpcService extends GreeterImplBase {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void sayHello(HelloRequest request ,StreamObserver<HelloReply> responseObserver){
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void discovery(HelloRequest request ,StreamObserver<DiscoveryReply> responseObserver){
        DiscoveryReply reply = DiscoveryReply.newBuilder().addAllServices(discoveryClient.getServices()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
