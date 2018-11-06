package com.lisg.user.service;

import com.lisg.user.threadPool.UserInfoThreadPool;
import com.lisg.user.threadPool.UserLoader;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lisg on 2018/11/5.
 */
@Service
public class UserService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String login(String userId, String password) {
        HashOperations<String, Object, Object> cityHash = redisTemplate.opsForHash();

        //登录成功，生成token保存在redis，并返回给客户端
        if ("lisg".equals(userId) && "123456".equals(password)) {
            Date loginTime_ = new Date();
            Date expireTime_ = DateUtils.addMinutes(loginTime_, 30);

            String loginTime = DateFormatUtils.format(loginTime_, "yyyyMMddHHmmss");
            String expireTime = DateFormatUtils.format(expireTime_, "yyyyMMddHHmmss");

            String token = userId+"$"+loginTime;
            String cacheKey = "x-token" + ":" + userId;
            String cacheValue = token + ":" + expireTime;

            cityHash.put("chengdu:userToken", cacheKey, cacheValue);
            System.err.println("----token: " + cityHash.get("chengdu:userToken", cacheKey));

            //异步加载用户信息
            UserInfoThreadPool.submit(new UserLoader("lisg"));
            return token;
        } else {
            return "登录失败！";
        }

    }
}
