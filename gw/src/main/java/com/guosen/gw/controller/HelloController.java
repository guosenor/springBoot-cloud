package com.guosen.gw.controller;

import com.guosen.gw.service.HelloGrpcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "类描述", tags = { "hello" })
@RestController
public class HelloController {
   
    @Autowired
    private HelloGrpcService helloGrpcService;

    @Autowired
    DiscoveryClient dc;

    @ApiOperation(value = "service-user say hellow")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
         String msg = helloGrpcService.sayHello("guosen");
         return msg;
    }
    @ApiOperation(value = "service-user discover")
    @RequestMapping( value = "/serviceDiscovery",method = RequestMethod.GET)
    public Object serviceDiscovery() {
         Object serviceList = helloGrpcService.discovery("guosen");
         return serviceList;
    }
    @ApiOperation(value = "gw discover")
    @RequestMapping(value = "/discovery",method = RequestMethod.GET)
    public Object discovery() {
         return this.dc;
    }
}
