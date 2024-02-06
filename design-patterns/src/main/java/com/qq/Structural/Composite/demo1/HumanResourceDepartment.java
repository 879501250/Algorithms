package com.qq.Structural.Composite.demo1;

/**
 * 人力资源部
 */
public class HumanResourceDepartment implements Department {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void duty() {
        System.out.println("人力资源部负责员工招聘培训管理");
    }
}
