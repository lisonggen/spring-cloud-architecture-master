package com.lisg.user.fallback;

import com.lisg.order.SpringOrder;
import com.lisg.order.api.feign.OrderFeignClient;
import com.lisg.order.api.feign.OrderFeignClient.OrderFeignClientHystrixFallBack;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lisg on 2018/11/6.
 */
@Component
public class OrderFeignClientFallback extends OrderFeignClientHystrixFallBack {
    @Override
    public SpringOrder getOrder(@RequestParam("orderId") String orderId, @RequestParam("userId") String userId) {
        return new SpringOrder();
    }

    @Override
    public String getOrderList(@RequestParam("userId") String userId) {
        return "order feign client fall back: getOrderList";
    }
}
