package com.lisg.order.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by lisg on 2018/11/6.
 */
@EnableFeignClients("com.lisg.order.api.feign")	//启用内部代理
@EnableCircuitBreaker                            //启用断路器
@ConditionalOnProperty(prefix="architecture-orderservice.api.feign", name = {"enabled"}, havingValue="true", matchIfMissing = false)
public class OrderServiceApiAutoConfiguration {
}
