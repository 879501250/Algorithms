package com.qq.Behavioral.ChainOfResponsibility.demo1;

/**
 * CEO 类，审批 10000 元以内
 */
public class CEO {
    private String name;

    public CEO(String name) {
        this.name = name;
    }

    public boolean approve(int amount) {
        if (amount <= 10000) {
            System.out.println("审批通过。【CEO：" + name + "】【金额：" + amount + "】");
            return true;
        } else {
            System.out.println("驳回申请。【CEO：" + name + "】【金额：" + amount + "】");
            return false;
        }
    }
}
