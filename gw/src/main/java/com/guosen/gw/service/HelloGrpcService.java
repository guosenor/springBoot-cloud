package com.guosen.gw.service;

import com.guosen.serviceuser.proto.helloworld.HelloReply;
import com.guosen.serviceuser.proto.helloworld.HelloRequest;
import com.guosen.serviceuser.proto.helloworld.GreeterGrpc.GreeterBlockingStub;

import org.springframework.stereotype.Service;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class HelloGrpcService {

    @GrpcClient("service-user")
    private GreeterBlockingStub stub;

    public String sayHello(String msg) {
        try {
            HelloReply response = this.stub.sayHello(HelloRequest.newBuilder().setName(msg).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            //TODO: handle exception
            return "FAILED with " + e.getStatus().getCode();
        }
 
    }
}
