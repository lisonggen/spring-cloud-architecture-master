package com.lisg.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients        //启用代理
@EnableCircuitBreaker    //启用断路器
@EnableDiscoveryClient	//标识具体的一个服务,需要向注册中心注册
@MapperScan("com.lisg.user.mapper")
@SpringBootApplication	//SpringBoot 核心配置
public class UserApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(UserApplication.class, args);
	}
	
}
