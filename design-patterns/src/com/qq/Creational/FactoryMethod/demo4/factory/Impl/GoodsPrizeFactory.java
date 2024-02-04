package com.qq.Creational.FactoryMethod.demo4.factory.Impl;

import com.qq.Creational.FactoryMethod.demo4.factory.IPrizeFactory;
import com.qq.Creational.FactoryMethod.demo4.model.IPrize;
import com.qq.Creational.FactoryMethod.demo4.model.Impl.GoodsPrize;

/**
 * 专门生产实物商品的工厂
 */
public class GoodsPrizeFactory implements IPrizeFactory {
    @Override
    public IPrize getPrize() {
        return new GoodsPrize();
    }
}
