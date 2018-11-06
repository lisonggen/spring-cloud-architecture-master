package com.lisg.order.api.feign;

import com.lisg.order.SpringOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= OrderFeignConstants.FEIGN_NAME, fallback = OrderFeignClient.OrderFeignClientHystrixFallBack.class)
public interface OrderFeignClient {

	@RequestMapping(value="/getOrder")
	public SpringOrder getOrder(@RequestParam("orderId") String orderId, @RequestParam("userId") String userId);
	
	
	@RequestMapping(value="/getOrderList")
	public String getOrderList(@RequestParam("userId") String userId);
	
	
	public abstract class OrderFeignClientHystrixFallBack implements OrderFeignClient {
		
	}
	
	
	
}
