package com.guosen.serviceuser;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ecwid.consul.v1.agent.model.NewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;

import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;


@Configuration
public class AfterStartUp implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private ConsulServiceRegistry registry;
    
	@Autowired
	private ConsulDiscoveryProperties properties;
    @Value("${spring.application.name}")
    private String serverName;
    @Value("${grpc.server.port}")
    private Integer grpcPort;
    @Value("${server.port}")
    private Integer httpPort;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        NewService service = new NewService();
        service.setName(serverName);
        service.setId(properties.getInstanceId());
        service.setPort(grpcPort);
        service.setAddress(properties.getHostname());
        NewService.Check check = new NewService.Check();
        check.setGrpc(service.getAddress()+":"+service.getPort()+"/grpc.health.v1.Health/Check");
        check.setGrpcUseTLS(false);
        // check.setHttp("http://"+service.getAddress()+":"+httpPort+properties.getHealthCheckPath());
        // check.setTcp("tcp:"+service.getPort());
        check.setInterval("15s");
        service.setCheck(check);
        // System.out.println(service);
        // System.out.println(properties);
        ConsulRegistration registration =  new ConsulRegistration(service, properties);
        
        registry.register(registration);
    }    
}
