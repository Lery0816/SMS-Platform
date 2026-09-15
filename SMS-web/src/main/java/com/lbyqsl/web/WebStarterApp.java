package com.lbyqsl.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lbyqsl
 * @description
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lbyqsl.web.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class WebStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(WebStarterApp.class, args);
    }

}
