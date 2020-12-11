package com.guosen.serviceuser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  
@RestController  
public class ConsulApp {  
      
    @RequestMapping("/health")  
    public Object home() {  
        return "server-user";  
    }
}
