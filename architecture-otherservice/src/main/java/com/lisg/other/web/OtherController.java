package com.lisg.other.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lisg on 2018/11/18.
 */
@RestController
public class OtherController {

    @RequestMapping("/other")
    public String other() {
        System.err.println("------other");
        return "other";
    }
}
