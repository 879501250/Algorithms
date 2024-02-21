package com.qq.Behavioral.ChainOfResponsibility.demo2;

/**
 *员工，只能审批 1000 元以内
 */
public class Staff extends Approver {

    public Staff(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 1000) {
            System.out.println("审批通过。【员工：" + name + "】【金额： + amount + 】");
        } else {
            System.out.println("无权审批，升级处理。【员工：" + name + "】");
            this.nextApprover.approve(amount);
        }
    }

}
