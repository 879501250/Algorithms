package com.qq.Structural.Decorator.demo2;

import com.qq.Structural.Decorator.common.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录接口
 */
public class LoginSsoDecorator implements HandlerInterceptor {

    private HandlerInterceptor handlerInterceptor;
    private static Map<String, String> authMap = new ConcurrentHashMap<>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = handlerInterceptor.preHandle(request, response, handler);
        if (!success) return false;
        String userId = request.substring(8);
        String method = authMap.get(userId);
        System.out.println("模拟单点登录方法访问拦截校验：[" + userId + "] [" + method + "]");
        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }
}
