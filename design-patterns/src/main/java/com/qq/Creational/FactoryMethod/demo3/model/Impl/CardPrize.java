package com.qq.Creational.FactoryMethod.demo3.model.Impl;

import com.qq.Creational.FactoryMethod.demo2.model.IPrize;

import java.util.Map;

/**
 * 兑换卡类
 */
public class CardPrize implements IPrize {
    @Override
    public void sendPrize(String userId, Map<String, String> extMap) throws Exception {
        System.out.println("调用发送第三方兑换卡的逻辑");
    }
}
