package com.qq.Structural.Adapter.demo1;

import com.qq.Structural.Adapter.common.service.OrderService;
import com.qq.Structural.Adapter.common.service.POPOrderService;

/**
 * 订单控制器
 */
public class OrderController {
    /**
     * 判断用户是否是第一次购买
     *
     * @param uid   用户id
     * @param isPOP 是否是第三方平台
     * @return
     */
    public boolean isFirst(String uid, boolean isPOP) {
        if (isPOP) {
            return new POPOrderService().isFirstOrder(uid);
        } else {
            return new OrderService().queryUserOrderCount(uid) == 0;
        }
    }
}
