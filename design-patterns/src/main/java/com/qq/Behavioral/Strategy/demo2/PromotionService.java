package com.qq.Behavioral.Strategy.demo2;

/**
 * 促销活动服务
 */
public class PromotionService {
    /**
     * 执行促销活动
     *
     * @param type 促销活动类型
     */
    public void doPromotion(String type) {
        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(type);
        promotionStrategy.doPromotion();
    }
}
