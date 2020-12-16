package com.guosen.gw.controller;

import com.alibaba.fastjson.JSONObject;
import com.guosen.gw.pojo.UserForm;
import com.guosen.gw.util.HandleFormValidateError;
import com.guosen.serviceuser.proto.user.UserFormRequest;
import com.guosen.serviceuser.proto.user.UserGrpc;
import com.guosen.serviceuser.proto.user.UserReply;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.devh.boot.grpc.client.inject.GrpcClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "类描述", tags = { "User" })

@RestController
public class UserController {

     @GrpcClient("service-user")
     private UserGrpc.UserBlockingStub stub;

     @ApiOperation(value = "用户登录")
     @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json")
     @ResponseBody
     public String login(@RequestBody @Validated UserForm form, BindingResult userFormBindingResult) {
          if (userFormBindingResult.hasErrors()) {
               return HandleFormValidateError.format(userFormBindingResult.getFieldErrors());
          }
          UserReply user = this.stub
                    .login(UserFormRequest.newBuilder().setUsername(form.username).setPassword(form.password).build());
          if(user.getId()!=0){
               return "{\"status\":\"success\"}";
          }
          return "{\"status\":\"fail\"}";
     }

     @ApiOperation(value = "用户注册")
     @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
     @ResponseBody
     public String register(@RequestBody @Validated UserForm form, BindingResult userFormBindingResult) {
          if (userFormBindingResult.hasErrors()) {
               return HandleFormValidateError.format(userFormBindingResult.getFieldErrors());
          }
          UserReply user = this.stub
                    .create(UserFormRequest.newBuilder().setUsername(form.username).setPassword(form.password).build());
          if(user.getId()!=0){
               return "{\"status\":\"success\"}";
          }
          return "{\"status\":\"fail\"}";
     }
}
