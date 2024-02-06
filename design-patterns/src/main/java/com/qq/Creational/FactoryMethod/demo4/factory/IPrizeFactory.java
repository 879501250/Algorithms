package com.qq.Creational.FactoryMethod.demo4.factory;

import com.qq.Creational.FactoryMethod.demo4.model.IPrize;

/**
 * 奖品工厂接口，不同的实现接口能够生产不同的奖品
 */
public interface IPrizeFactory {
    /**
     * 生成奖品
     *
     * @return
     */
    IPrize getPrize();
}
