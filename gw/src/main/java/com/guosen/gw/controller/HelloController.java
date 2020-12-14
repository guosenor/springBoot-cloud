package com.guosen.gw.controller;


import com.guosen.gw.service.HelloGrpcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {
   
    @Autowired
    private HelloGrpcService helloGrpcService;

    @Autowired
    DiscoveryClient dc;

    @RequestMapping("/hello")
    public String hello() {
         String msg = helloGrpcService.sayHello("guosen");
         return msg;
    }
    @RequestMapping("/serviceDiscovery")
    public Object serviceDiscovery() {
         Object serviceList = helloGrpcService.discovery("guosen");
         return serviceList;
    }
    @RequestMapping("/discovery")
    public Object discovery() {
         return this.dc;
    }
}
