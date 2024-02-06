package com.qq.Creational.FactoryMethod.demo4.factory.Impl;

import com.qq.Creational.FactoryMethod.demo4.factory.IPrizeFactory;
import com.qq.Creational.FactoryMethod.demo4.model.IPrize;
import com.qq.Creational.FactoryMethod.demo4.model.Impl.CardPrize;

/**
 * 专门生产兑换卡的工厂
 */
public class CardPrizeFactory implements IPrizeFactory {
    @Override
    public IPrize getPrize() {
        return new CardPrize();
    }
}
