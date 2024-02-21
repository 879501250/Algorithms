package com.qq.Behavioral.Strategy.demo2;

import com.qq.Behavioral.Strategy.demo2.Impl.EmptyPromotionStrategy;
import com.qq.Behavioral.Strategy.demo2.Impl.FanXianPromotionStrategy;
import com.qq.Behavioral.Strategy.demo2.Impl.ManJianPromotionStrategy;

import java.util.HashMap;

/**
 * 促销策略工厂
 */
public class PromotionStrategyFactory {
    private static HashMap<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.MANJIAN, new ManJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.FANXIAN, new FanXianPromotionStrategy());
    }

    /**
     * 构造函数不能被外界访问
     */
    private PromotionStrategyFactory() {
    }

    /**
     * 根据传入的键值获取相应的促销策略
     *
     * @param promotionKey
     * @return
     */
    public static PromotionStrategy getPromotionStrategy(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy != null ? promotionStrategy : new EmptyPromotionStrategy();
    }

    /**
     * 使用这种方式声明常量 , 可以起到逻辑上分组的作用
     */
    public interface PromotionKey {
        String MANJIAN = "ManJian";
        String FANXIAN = "FanXian";
    }
}
