package com.qq.Structural.Composite.demo1;

public class Test {
    public static void main(String[] args) {
        Organization hq = new Headquarters();

        Department fnc = new FinanceDepartment();

        Department hr = new HumanResourceDepartment();

        hq.addOrg(fnc);
        hq.addOrg(hr);

        System.out.println("====组织结构====");
        hq.showOrg();
        System.out.println("====部门职责====");
        hq.displayDuty();
    }
}
