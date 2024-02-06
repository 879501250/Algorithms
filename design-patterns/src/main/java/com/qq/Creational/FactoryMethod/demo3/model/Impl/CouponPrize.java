package com.qq.Creational.FactoryMethod.demo3.model.Impl;

import com.qq.Creational.FactoryMethod.demo2.model.IPrize;

import java.util.Map;

/**
 * 优惠卷类
 */
public class CouponPrize implements IPrize {
    @Override
    public void sendPrize(String userId, Map<String, String> extMap) throws Exception {
        System.out.println("调用发送优惠卷的逻辑");
    }
}
