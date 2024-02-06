package com.qq.Structural.Adapter.demo2.impl;

import com.qq.Structural.Adapter.common.service.POPOrderService;
import com.qq.Structural.Adapter.demo2.OrderAdapterService;

/**
 * 第三方商品接口
 */
public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}