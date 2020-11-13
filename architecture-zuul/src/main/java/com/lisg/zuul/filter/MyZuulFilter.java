package com.lisg.zuul.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.util.Date;

@Component
public class MyZuulFilter extends ZuulFilter {

    @Value("${zuul.authFilter.ignoreUrl}")
    private String[] ignoreUrl;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 表示当前的过滤器是否被调用
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filterOrder表示执行的优先级，值越小表示优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * pre:		在请求被路由之前调用
     * routing: 	在请求被路由之中调用
     * post: 		在请求被路由之后调用
     * error: 		处理请求发生错误时调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 真正执行Filter逻辑的方法
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //1:获取请求携带的token
        String token = request.getHeader("x-auth-token");
        String url = request.getRequestURI();

        //2:判断请求是否需要登录验证
        boolean needCheck = needCheck(url);
        if (needCheck) {
            //token为空
            if (StringUtils.isBlank(token)) {
                setResponseErrorInfo(ctx, 400, "token is empty!");
            } else {
                //获取userId
                String userId = request.getParameter("userId");
                //通过userId，从redis获取token,和请求端携带的token进行比较
                HashOperations<String, Object, Object> hoHash = redisTemplate.opsForHash();
                String cacheKey = "x-token" + ":" + userId;
                String cacheToken = (String) hoHash.get("chengdu:userToken", cacheKey);

                if (StringUtils.isNotBlank(cacheToken)) {
                    //拆分cacheToken,比较token是否相等，判断是否过时
                    String[] tokens = cacheToken.split(":");
                    String nowTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
                    if (!token.equals(tokens[0])) {
                        setResponseErrorInfo(ctx, 401, "token is not correct!");
                    } else if (Long.parseLong(nowTime) > Long.parseLong(tokens[1])) {
                        setResponseErrorInfo(ctx, 402, "token is expired!");
                        //如果token过期，则删掉
                        hoHash.delete("chengdu:userToken", "x-token" + ":" + userId);
                    }
                } else {
                    setResponseErrorInfo(ctx, 403, "please login!");
                }
            }
        }

        return ctx;
    }

    //判断一个请求，是否需要登录验证
    public boolean needCheck(String url) {
        boolean needCheck = true;
        for (String igoUrl : ignoreUrl) {
            if (url.startsWith(igoUrl)) {
                return false;
            }
            break;
        }
        return needCheck;
    }

    //设置返回结果
    private void setResponseErrorInfo(RequestContext ctx, int responseStatusCode, String responseBody) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(responseStatusCode);
        ctx.setResponseBody(responseBody);
    }

}
