package com.qq.Structural.Adapter.demo2.impl;

import com.qq.Structural.Adapter.common.service.OrderService;
import com.qq.Structural.Adapter.demo2.OrderAdapterService;

/**
 * 内部商品接口
 */
public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1;
    }

}
