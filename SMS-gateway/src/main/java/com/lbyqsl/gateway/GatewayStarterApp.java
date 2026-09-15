package com.lbyqsl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lbyqsl
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableDynamicThreadPool
@EnableFeignClients
public class GatewayStarterApp {

        public static void main(String[] args) {
        SpringApplication.run(GatewayStarterApp.class,args);
    }
}
