package com.qq.Behavioral.ChainOfResponsibility.demo1;

/**
 * 员工，只能审批 1000 元以内
 */
public class Staff {
    private String name;

    public Staff(String name) {
        this.name = name;
    }

    public boolean approve(int amount) {
        if (amount <= 1000) {
            System.out.println("审批通过。【员工：" + name + "】【金额：" + amount + "】");
            return true;
        } else {
            System.out.println("无权审批，请找上级。【员工：" + name + "】");
            return false;
        }
    }
}
