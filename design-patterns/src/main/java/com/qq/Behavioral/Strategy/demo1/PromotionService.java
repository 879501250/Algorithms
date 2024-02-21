package com.qq.Behavioral.Strategy.demo1;

/**
 * 促销活动服务
 */
public class PromotionService {
    /**
     * 计算不同类型的优惠券折扣后的实际金额
     *
     * @param type        优惠券类型
     * @param typeContent 优惠券金额
     * @param skuPrice    商品金额
     * @param typeExt     满减（代表一些拓展信息）
     * @return
     */
    public double discountAmount(int type, double typeContent, double skuPrice, double typeExt) {
        // 1. 直减券
        if (1 == type) {
            return skuPrice - typeContent;
        }
        // 2. 满减券
        if (2 == type) {
            if (skuPrice < typeExt) return skuPrice;
            return skuPrice - typeContent;
        }
        // 3. 折扣券
        if (3 == type) {
            return skuPrice * typeContent;
        }
        // 4. n元购
        if (4 == type) {
            return typeContent;
        }
        return 0D;
    }
}
