package com.qq.Structural.Bridge.demo2.mode;

/**
 * 支付模式接口，具体支付方式由子类实现
 */
public interface IPayMode {
    boolean security(String uId);
}
