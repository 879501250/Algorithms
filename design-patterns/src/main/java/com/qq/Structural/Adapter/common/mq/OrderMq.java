package com.qq.Structural.Adapter.common.mq;

import java.util.Date;

/**
 * 内部订单的 MQ 消息格式
 */
public class OrderMq {
    // 用户ID
    private String uid;
    // 商品
    private String sku;
    // 订单ID
    private String orderId;
    // 下单时间
    private Date createOrderTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }
}
