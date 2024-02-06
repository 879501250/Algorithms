package com.qq.Structural.Composite.demo2;

/**
 * 人力资源部
 */
public class HumanResourceDepartment implements Company {
    private String name = "";

    HumanResourceDepartment(String name) {
        this.name = name;
    }

    @Override
    public void showOrg(int dept) {
        print(dept);
        System.out.println(this.name);
    }
}
