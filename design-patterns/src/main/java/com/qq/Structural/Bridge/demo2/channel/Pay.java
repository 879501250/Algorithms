package com.qq.Structural.Bridge.demo2.channel;

import com.qq.Structural.Bridge.demo2.mode.IPayMode;

import java.math.BigDecimal;

/**
 * 支付类型
 */
public abstract class Pay {

    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);

}
