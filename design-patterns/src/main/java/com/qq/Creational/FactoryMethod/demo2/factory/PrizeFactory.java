package com.qq.Creational.FactoryMethod.demo2.factory;

import com.qq.Creational.FactoryMethod.demo2.model.IPrize;
import com.qq.Creational.FactoryMethod.demo2.model.Impl.CardPrize;
import com.qq.Creational.FactoryMethod.demo2.model.Impl.CouponPrize;
import com.qq.Creational.FactoryMethod.demo2.model.Impl.GoodsPrize;

/**
 * 奖品工厂，能够生产不同的奖品
 */
public class PrizeFactory {
    /**
     * 根据奖品类型不同生产不同的奖品
     *
     * @param prizeType 奖品类型
     * @return
     */
    public IPrize getPrize(Integer prizeType) {
        if (null == prizeType) return null;
        // 第三方兑换卡(爱奇艺)
        if (1 == prizeType) {
            return new CardPrize();
        }
        // 优惠券
        if (2 == prizeType) {
            return new CouponPrize();
        }
        // 实物商品
        if (3 == prizeType) {
            return new GoodsPrize();
        }
        throw new RuntimeException("不存在的奖品类型");
    }
}
