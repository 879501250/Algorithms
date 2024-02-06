package com.qq.Structural.Composite.demo2;

/**
 * 财务部
 */
public class FinanceDepartment implements Company {
    private String name = "";

    FinanceDepartment(String name) {
        this.name = name;
    }

    @Override
    public void showOrg(int dept) {
        print(dept);
        System.out.println(this.name);
    }
}
