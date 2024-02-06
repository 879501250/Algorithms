package com.qq.Structural.Adapter.common.service;

/**
 * 处理第三方普通的业务层
 */
public class POPOrderService {
    /**
     * 查询用户在该普通是否是首单
     *
     * @param uId 用户 id
     * @return
     */
    public boolean isFirstOrder(String uId) {
        System.out.println("POP商家，查询用户" + uId + "的订单是否为首单");
        return true;
    }
}
