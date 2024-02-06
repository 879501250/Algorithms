package com.qq.Creational.Builder.demo2;

/**
 * 装修控制器
 */
public class DecorationPackageController {
    /**
     * 打印装修详情
     *
     * @param area  装修面积
     * @param level 装修等级
     */
    public void getMatterList(Double area, Integer level) {
        Builder builder = new Builder();
        if (level == 1) {
            System.out.println(builder.levelOne(area).getDetail());
        } else if (level == 2) {
            System.out.println(builder.levelTwo(area).getDetail());
        } else if (level == 3) {
            System.out.println(builder.levelThree(area).getDetail());
        }
    }
}
