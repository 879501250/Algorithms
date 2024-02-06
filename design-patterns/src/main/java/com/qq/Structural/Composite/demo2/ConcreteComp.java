package com.qq.Structural.Composite.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 总部或分公司
 */
public class ConcreteComp implements Company {
    private String name = "";
    private List<Company> companies = new ArrayList<>();

    ConcreteComp(String name) {
        this.name = name;
    }

    @Override
    public void addOrg(Company company) {
        companies.add(company);
    }

    @Override
    public void removeOrg(Company company) {
        companies.remove(company);
    }

    @Override
    public void showOrg(int dept) {
        print(dept);
        System.out.println(this.name);
        for (Company c : companies) {
            c.showOrg(dept + 2);
        }
    }
}
