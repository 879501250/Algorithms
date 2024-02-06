package com.qq.Structural.Composite.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司总部
 */
public class Headquarters implements Organization {
    private List<Department> departments = new ArrayList<>();

    @Override
    public void addOrg(Department department) {
        departments.add(department);
    }

    @Override
    public void removeOrg(Department department) {
        departments.remove(department);
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void showOrg() {
        System.out.println("-" + getName());
        for (Department department : departments) {
            System.out.println("--" + department.getName());
        }
    }

    @Override
    public void displayDuty() {
        for (Department department : departments) {
            department.duty();
        }
    }
}
