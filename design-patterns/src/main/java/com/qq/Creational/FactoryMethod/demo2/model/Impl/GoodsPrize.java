package com.qq.Creational.FactoryMethod.demo2.model.Impl;

import com.qq.Creational.FactoryMethod.demo2.model.IPrize;

import java.util.Map;

/**
 * 实物商品类
 */
public class GoodsPrize implements IPrize {
    @Override
    public void sendPrize(String userId, Map<String, String> extMap) throws Exception {
        System.out.println("调用发送实物商品的逻辑");
    }
}