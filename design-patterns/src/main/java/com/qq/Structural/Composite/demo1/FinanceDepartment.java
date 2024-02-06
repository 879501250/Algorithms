package com.qq.Structural.Composite.demo1;

/**
 * 财务部
 */
public class FinanceDepartment implements Department {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void duty() {
        System.out.println("财务部负责公司财务收支管理");
    }
}
