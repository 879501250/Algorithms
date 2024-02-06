package com.qq.Structural.Adapter.common.service;

/**
 * 处理内部订单的业务层
 */
public class OrderService {
    /**
     * 查询指定用户的订单数量
     *
     * @param userId 用户 id
     * @return
     */
    public long queryUserOrderCount(String userId) {
        System.out.println("自营商家，查询用户[" + userId + "]的订单数量");
        return 10L;
    }
}
