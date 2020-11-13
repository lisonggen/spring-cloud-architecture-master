package com.lisg.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by lisg on 2018/11/8.
 */
@EnableDiscoveryClient
@zipkin.server.internal.EnableZipkinServer
@SpringBootApplication    //SpringBoot 核心配置
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
