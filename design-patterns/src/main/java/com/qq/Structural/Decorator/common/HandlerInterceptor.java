package com.qq.Structural.Decorator.common;

/**
 * 模拟 Spring 的 HandlerInterceptor
 * 实际的单点登录开发会基于 org.springframework.web.servlet.HandlerInterceptor 实现
 */
public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}