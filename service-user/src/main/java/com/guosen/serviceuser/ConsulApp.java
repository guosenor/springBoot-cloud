package com.guosen.serviceuser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  
@EnableDiscoveryClient  
@RestController  
public class ConsulApp {  
      
    @RequestMapping("/health")  
    public Object home() {  
        return "server-user";  
    }
}
