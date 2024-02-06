package com.qq.Structural.Flyweight.demo2;

import com.qq.Structural.Flyweight.common.Goods;
import com.qq.Structural.Flyweight.common.Stock;

/**
 * 商品控制器
 */
public class GoodsController {
    private RedisUtils redisUtils = new RedisUtils();

    public Goods queryActivityInfo(Long id) {
        Goods goods = GoodsFactory.getActivity(id);
        // 模拟从Redis中获取库存变化信息
        Stock stock = new Stock(1000, redisUtils.getStockUsed());
        goods.setStock(stock);
        return goods;
    }
}
