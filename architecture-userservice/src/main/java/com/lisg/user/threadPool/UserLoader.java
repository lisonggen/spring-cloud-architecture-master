package com.lisg.user.threadPool;

import com.lisg.user.util.FastJsonConvertUtil;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lisg on 2018/11/5.
 */
public class UserLoader implements Runnable {

    private String userId;

    public UserLoader(String userId){
        this.userId = userId;
    }

    @Override
    public void run() {
        @SuppressWarnings("unchecked")
        RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ApplicationFactory.getBean("redisTemplate");
        System.err.println("-----------:" + redisTemplate);
        HashOperations<String, Object, Object> userInfoHash = redisTemplate.opsForHash();
        //比如用户登录成功,去用户表里面获取用户的角色、权限、相关的描述信息
        //select * from user_info where userId = ?
        //大型互联公司 没有去做复杂join查询  都是单独进行查询[单表查询]
        String address = "北京市朝阳区酒仙桥中路";
        String phone = "12345678901";
        String role = "admin";
        String level = "10";

        Map<String, String> userInfos = new HashMap<>();
        userInfos.put("address", address);
        userInfos.put("phone", phone);
        userInfos.put("role", role);
        userInfos.put("level", level);
        userInfoHash.put("chengdu:userinfo", userId, FastJsonConvertUtil.convertObjectToJSON(userInfos));
    }
}
