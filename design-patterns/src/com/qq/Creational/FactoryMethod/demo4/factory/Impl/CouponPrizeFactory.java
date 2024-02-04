package com.qq.Creational.FactoryMethod.demo4.factory.Impl;

import com.qq.Creational.FactoryMethod.demo4.factory.IPrizeFactory;
import com.qq.Creational.FactoryMethod.demo4.model.IPrize;
import com.qq.Creational.FactoryMethod.demo4.model.Impl.CouponPrize;

/**
 * 专门生产优惠卷的工厂
 */
public class CouponPrizeFactory implements IPrizeFactory {
    @Override
    public IPrize getPrize() {
        return new CouponPrize();
    }
}
