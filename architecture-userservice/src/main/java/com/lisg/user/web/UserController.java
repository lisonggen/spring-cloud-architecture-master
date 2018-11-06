package com.lisg.user.web;

import com.lisg.order.api.feign.OrderFeignClient;
import com.lisg.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lisg on 2018/11/5.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(@RequestParam String userId, @RequestParam String password) {
        return userService.login(userId, password);
    }
}
