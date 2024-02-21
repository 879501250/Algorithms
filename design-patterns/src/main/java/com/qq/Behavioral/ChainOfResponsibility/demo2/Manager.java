package com.qq.Behavioral.ChainOfResponsibility.demo2;

/**
 * 经理类，审批 5000 元以内
 */
public class Manager extends Approver {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 1000) {
            System.out.println("审批通过。【经理：" + name + "】【金额： + amount + 】");
        } else {
            System.out.println("无权审批，升级处理。【经理：" + name + "】");
            this.nextApprover.approve(amount);
        }
    }
}
