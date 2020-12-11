package com.guosen.serviceuser.grpcService;


import io.grpc.health.v1.*;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HealthGrpcService extends HealthGrpc.HealthImplBase {
    @Override
    public void check(HealthCheckRequest request ,StreamObserver<HealthCheckResponse> responseObserver){
        // System.out.println("check service:"+request.getService());
        HealthCheckResponse reply = HealthCheckResponse.newBuilder().setStatusValue(1).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
