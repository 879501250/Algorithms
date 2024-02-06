package com.qq.Structural.Flyweight.demo1;

import com.qq.Structural.Flyweight.common.Goods;
import com.qq.Structural.Flyweight.common.Stock;

/**
 * 商品控制器
 */
public class GoodsController {
    public Goods queryActivityInfo(Long id) {
        // 模拟从实际业务应用从接口中获取活动信息
        Goods goods = new Goods();
        goods.setId(10001L);
        goods.setName("图书嗨乐");
        goods.setDesc("图书优惠券分享激励分享活动第二期");
        goods.setStock(new Stock(1000, 1));
        return goods;
    }
}
