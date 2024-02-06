package com.qq.Structural.Decorator.common;

/**
 * 单点登录拦截器
 */
public class SsoInterceptor implements HandlerInterceptor {

    public boolean preHandle(String request, String response, Object handler) {
        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        return ticket.equals("success");
    }

}