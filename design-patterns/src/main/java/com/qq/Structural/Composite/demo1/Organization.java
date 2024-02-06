package com.qq.Structural.Composite.demo1;

/**
 * 组织
 */
public interface Organization {
    void addOrg(Department department);

    void removeOrg(Department department);

    String getName();

    void showOrg();

    void displayDuty();
}
