package com.lisg.order.service;

import com.lisg.order.entity.SpringOrder;
import com.lisg.order.mapper.SpringOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lisg on 2018/11/6.
 */
@Service
public class OrderService {
    @Resource
    private SpringOrderMapper springOrderMapper;

    public SpringOrder selectOrderById(String orderId) {
        return springOrderMapper.selectByOrderId(orderId);
    }
}
