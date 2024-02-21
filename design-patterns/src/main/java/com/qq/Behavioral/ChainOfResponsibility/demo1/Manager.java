package com.qq.Behavioral.ChainOfResponsibility.demo1;

/**
 * 经理类，审批 5000 元以内
 */
public class Manager {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public boolean approve(int amount) {
        if (amount <= 5000) {
            System.out.println("审批通过。【经理：" + name + "】【金额：" + amount + "】");
            return true;
        } else {
            System.out.println("无权审批，请找上级。【经理：" + name + "】");
            return false;
        }
    }
}
