package com.lisg.order.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lisg on 2018/11/6.
 */
@RestController
public class OrderController {

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public String getOrder(@RequestParam String orderId, @RequestParam String userId) {
        return "获取单个订单成功！";
    }

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public String getOrderList(@RequestParam String userId) {
        return "获取订单列表成功！";
    }
}
