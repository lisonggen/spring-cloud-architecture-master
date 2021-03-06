package com.lisg.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication    //SpringBoot 核心配置
public class OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }

}
