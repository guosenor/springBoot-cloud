package com.guosen.gw;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  
@EnableDiscoveryClient(autoRegister = false)
@RestController  
public class ConsulApp {  
      
    @RequestMapping("/health")  
    public Object home() {  
        return "gw";  
    }
}
