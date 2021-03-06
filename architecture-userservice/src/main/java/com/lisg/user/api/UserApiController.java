package com.lisg.user.api;

import com.lisg.order.SpringOrder;
import com.lisg.order.api.feign.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by lisg on 2018/11/6.
 */
@RestController
public class UserApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private OrderFeignClient orderFeignClient;

    @RequestMapping(value = "/getOrder")
    public SpringOrder getOrder(@RequestParam String orderId, @RequestParam String userId) {
        return orderFeignClient.getOrder(orderId, userId);
    }

    @RequestMapping(value = "/getOrderList")
    public String getOrderList(@RequestParam String userId) {
//        return restTemplate.getForObject("http://architecture-orderservice/getOrderList?userId={1}", String.class, userId);
        return orderFeignClient.getOrderList(userId);
    }
}
