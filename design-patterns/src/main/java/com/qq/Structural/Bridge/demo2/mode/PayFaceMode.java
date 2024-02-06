package com.qq.Structural.Bridge.demo2.mode;

/**
 * 人脸支付
 */
public class PayFaceMode implements IPayMode {

    public boolean security(String uId) {
        System.out.println("人脸支付，风控校验脸部识别");
        return true;
    }

}
