package com.qq.Structural.Adapter.common.mq;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 第三方订单的 MQ 消息格式
 */
public class POPOrderDelivered {
    // 用户ID
    private String uId;
    // 订单号
    private String orderId;
    // 下单时间
    private Date orderTime;
    // 商品
    private Date sku;
    // 商品名称
    private Date skuName;
    // 金额
    private BigDecimal decimal;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getSku() {
        return sku;
    }

    public void setSku(Date sku) {
        this.sku = sku;
    }

    public Date getSkuName() {
        return skuName;
    }

    public void setSkuName(Date skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
