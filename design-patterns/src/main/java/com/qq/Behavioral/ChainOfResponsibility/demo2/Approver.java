package com.qq.Behavioral.ChainOfResponsibility.demo2;

/**
 * 审批人抽象类
 */
public abstract class Approver {
    // 抽象出审批人的姓名。
    protected String name;
    // 下一个审批人，更高级别领导。
    // 使用 protected 修饰的目的是让子类可以获取到该类
    protected Approver nextApprover;

    public Approver(String name) {
        this.name = name;
    }

    // 返回下个审批人，链式编程。
    protected Approver setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
        return this.nextApprover;
    }

    // 抽象审批方法由具体审批人子类实现
    public abstract void approve(int amount);
}
