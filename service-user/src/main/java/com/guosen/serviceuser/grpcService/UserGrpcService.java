package com.guosen.serviceuser.grpcService;

import com.guosen.serviceuser.domain.User;
import com.guosen.serviceuser.mapper.UserMapper;
import com.guosen.serviceuser.proto.user.UserFormRequest;
import com.guosen.serviceuser.proto.user.UserGrpc;
import com.guosen.serviceuser.proto.user.UserInfo;
import com.guosen.serviceuser.proto.user.UserReply;
import com.guosen.serviceuser.proto.user.UserReply.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcService extends  UserGrpc.UserImplBase {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public void login(UserFormRequest request,StreamObserver<UserReply> responseObserver){
       User user =  userMapper.findByUsername(request.getUsername());
       Builder reply = UserReply.newBuilder();
       if(user!=null && user.checkPwd(request.getPassword())){
        reply.setCode(0);
       }else{
           reply.setCode(1);
           reply.setUser(UserInfo.newBuilder().setId(user.getId()).setUsername(user.getUserName()));
       }
       responseObserver.onNext(reply.build());
       responseObserver.onCompleted();
    }

    @Override
    public void create(UserFormRequest request,StreamObserver<UserReply> responseObserver){
       userMapper.create(request.getUsername(),BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()) );
       Builder reply = UserReply.newBuilder();
       reply.setCode(0);
       responseObserver.onNext(reply.build());
       responseObserver.onCompleted();
    }
}
