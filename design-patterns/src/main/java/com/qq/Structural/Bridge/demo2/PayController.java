package com.qq.Structural.Bridge.demo2;

import com.qq.Structural.Bridge.demo2.channel.Pay;
import com.qq.Structural.Bridge.demo2.channel.WxPay;
import com.qq.Structural.Bridge.demo2.channel.ZfbPay;
import com.qq.Structural.Bridge.demo2.mode.IPayMode;
import com.qq.Structural.Bridge.demo2.mode.PayCypher;
import com.qq.Structural.Bridge.demo2.mode.PayFaceMode;
import com.qq.Structural.Bridge.demo2.mode.PayFingerprintMode;

import java.math.BigDecimal;

/**
 * 支付控制器
 */
public class PayController {
    /**
     * 完成支付
     *
     * @param uId         用户 id
     * @param tradeId     交易 id
     * @param amount      交易金额
     * @param channelType 支付渠道
     * @param modeType    支付方式
     * @return
     */
    public boolean doPay(String uId, String tradeId, BigDecimal amount, int channelType, int modeType) {
        IPayMode payMode = null;
        if (1 == modeType) { // 密码支付
            payMode = new PayCypher();
        } else if (2 == modeType) { // 人脸支付
            payMode = new PayFaceMode();
        } else if (3 == modeType) { // 指纹支付
            payMode = new PayFingerprintMode();
        }
        Pay pay = null;
        if (1 == channelType) { // 微信支付
            pay = new WxPay(payMode);
        } else if (2 == channelType) { // 支付宝支付
            pay = new ZfbPay(payMode);
        }
        pay.transfer(uId, tradeId, amount);
        return true;
    }
}
