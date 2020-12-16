package com.guosen.gw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "类描述", tags = { "Service" })
@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Autowired
    private DiscoveryClient discoveryClient;

   /**
     * 获取所有服务
     */
    @ApiOperation(value = "查看gw发现的service-user服务")
    @RequestMapping(value = "/services", method = RequestMethod.POST, produces = "application/json")
    public Object services() {
        return discoveryClient.getInstances("service-user");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */

    @ApiOperation(value = "模拟轮训 gw 发现的 service-user 的服务")
    @RequestMapping(value = "/discover", method = RequestMethod.POST, produces = "application/json")
    public Object discover() {
        return loadBalancerClient.choose("service-user").getUri().toString();
    }
}
