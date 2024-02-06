package com.qq.Creational.Builder.demo2.model.impl;

import com.qq.Creational.Builder.common.IMaterial;
import com.qq.Creational.Builder.demo2.model.IMenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DecorationPackageMenu implements IMenu {
    // 装修清单
    private List<IMaterial> list = new ArrayList<>();
    // 装修价格
    private BigDecimal price = BigDecimal.ZERO;
    // 面积
    private BigDecimal area;
    // 装修等级；豪华欧式、轻奢田园、现代简约
    private String grade;

    private DecorationPackageMenu() {
    }

    public DecorationPackageMenu(Double area, String grade) {
        this.area = new BigDecimal(area);
        this.grade = grade;
    }

    public IMenu appendCeiling(IMaterial material) {
        list.add(material);
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(material.price()));
        return this;
    }

    public IMenu appendCoat(IMaterial material) {
        list.add(material);
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(material.price()));
        return this;
    }

    public IMenu appendFloor(IMaterial material) {
        list.add(material);
        price = price.add(area.multiply(material.price()));
        return this;
    }

    public IMenu appendTile(IMaterial material) {
        list.add(material);
        price = price.add(area.multiply(material.price()));
        return this;
    }

    public String getDetail() {

        StringBuilder detail = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级：" + grade + "\r\n" +
                "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元\r\n" +
                "房屋面积：" + area.doubleValue() + " 平米\r\n" +
                "材料清单：\r\n");

        for (IMaterial material : list) {
            detail.append(material.scene()).append("：")
                    .append(material.brand()).append("、")
                    .append(material.model()).append("、平米价格：")
                    .append(material.price()).append(" 元。\n");
        }

        return detail.toString();
    }

}
