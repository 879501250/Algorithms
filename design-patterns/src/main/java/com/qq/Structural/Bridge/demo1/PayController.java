package com.qq.Structural.Bridge.demo1;

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
        if (1 == channelType) { // 微信支付
            System.out.println("模拟微信渠道支付划账开始。uId：" + uId + " tradeId：" + tradeId + " amount：" + amount);
            if (1 == modeType) {
                System.out.println("密码支付，风控校验环境安全");
            } else if (2 == modeType) {
                System.out.println("人脸支付，风控校验脸部识别");
            } else if (3 == modeType) {
                System.out.println("指纹支付，风控校验指纹信息");
            }
        } else if (2 == channelType) { // 支付宝支付
            System.out.println("模拟支付宝渠道支付划账开始。uId：" + uId + " tradeId：" + tradeId + " amount：" + amount);
            if (1 == modeType) {
                System.out.println("密码支付，风控校验环境安全");
            } else if (2 == modeType) {
                System.out.println("人脸支付，风控校验脸部识别");
            } else if (3 == modeType) {
                System.out.println("指纹支付，风控校验指纹信息");
            }
        }
        return true;
    }
}
