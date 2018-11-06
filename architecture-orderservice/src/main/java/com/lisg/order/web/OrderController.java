package com.lisg.order.web;

import com.lisg.order.entity.SpringOrder;
import com.lisg.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lisg on 2018/11/6.
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public String getOrder(@RequestParam String orderId, @RequestParam String userId) {
        SpringOrder order = orderService.selectOrderById(orderId);
        return "orderId: " + order.getOrderId() + "itemId: " + order.getItemId();
    }

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public String getOrderList(@RequestParam String userId) {
        try {
            System.err.println("-----getOrderList");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "获取订单列表成功！";
    }
}
