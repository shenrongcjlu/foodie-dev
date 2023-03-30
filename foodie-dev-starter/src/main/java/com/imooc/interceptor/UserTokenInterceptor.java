package com.imooc.interceptor;

import com.imooc.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/3/30 21:34
 */
@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {


    @Resource
    private RedisOperator redisOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("enter {}", this.getClass().getSimpleName());
        String headerUserId = request.getHeader("headerUserId");
        String headerUserToken = request.getHeader("headerUserToken");
        System.out.println(headerUserId);

//        if (StringUtils.isBlank(headerUserId) || StringUtils.isBlank(headerUserToken)) {
//            log.warn("用户未登录");
//            throw new BizException("用户未登录");
//        }
//
//        String redisToken = redisOperator.get(Constants.USER_TOKEN + ":" + headerUserId);
//        if (StringUtils.isBlank(redisToken)) {
//            log.warn("用户未登录");
//            return false;
//        }
//
//        if (!StringUtils.equals(headerUserToken, redisToken)) {
//            log.warn("用户在异地登录");
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
