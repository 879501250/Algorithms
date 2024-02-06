package com.qq.Structural.Flyweight.demo2;

import com.qq.Structural.Flyweight.common.Goods;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 */
public class GoodsFactory {
    static Map<Long, Goods> map = new HashMap<>();

    public static Goods getActivity(Long id) {
        Goods goods = map.get(id);
        if (null == goods) {
            // 模拟从实际业务应用从接口中获取活动信息
            goods = new Goods();
            goods.setId(10001L);
            goods.setName("图书嗨乐");
            goods.setDesc("图书优惠券分享激励分享活动第二期");
            map.put(id, goods);
        }
        return goods;
    }
}
