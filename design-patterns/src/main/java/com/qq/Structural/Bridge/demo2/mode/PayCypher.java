package com.qq.Structural.Bridge.demo2.mode;

/**
 * 密码支付
 */
public class PayCypher implements IPayMode {


    public boolean security(String uId) {
        System.out.println("密码支付，风控校验环境安全");
        return true;
    }

}
