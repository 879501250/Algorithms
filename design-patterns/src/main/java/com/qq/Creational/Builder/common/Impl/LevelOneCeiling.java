package com.qq.Creational.Builder.common.Impl;

import com.qq.Creational.Builder.common.IMaterial;

import java.math.BigDecimal;

/**
 * 一级吊顶
 */
public class LevelOneCeiling implements IMaterial {

    public String scene() {
        return "吊顶";
    }

    public String brand() {
        return "装修公司自带";
    }

    public String model() {
        return "一级顶";
    }

    public BigDecimal price() {
        return new BigDecimal(260);
    }

    public String desc() {
        return "造型只做低一级，只有一个层次的吊顶，一般离顶120-150mm";
    }

}
