package com.qq.Behavioral.ChainOfResponsibility.demo2;

/**
 * CEO 类，审批 10000 元以内
 */
public class CEO extends Approver {

    public CEO(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 1000) {
            System.out.println("审批通过。【CEO：" + name + "】【金额： + amount + 】");
        } else {
            System.out.println("驳回申请。【CEO：" + name + "】【金额：" + amount + "】");
            this.nextApprover.approve(amount);
        }
    }
}
