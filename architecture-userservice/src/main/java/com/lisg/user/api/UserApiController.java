package com.lisg.user.api;

import com.lisg.order.api.feign.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lisg on 2018/11/6.
 */
@RestController
public class UserApiController {

    @Resource
    private OrderFeignClient orderFeignClient;

    @RequestMapping(value = "/getOrder")
    public String getOrder(@RequestParam String orderId, @RequestParam String userId){
        return orderFeignClient.getOrder(orderId, userId);
    }

    @RequestMapping(value = "/getOrderList")
    public String getOrderList(@RequestParam String userId){
        return orderFeignClient.getOrderList(userId);
    }
}
