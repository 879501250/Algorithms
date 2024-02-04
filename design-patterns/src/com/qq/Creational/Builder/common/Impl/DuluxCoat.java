package com.qq.Creational.Builder.common.Impl;

import com.qq.Creational.Builder.common.IMaterial;

import java.math.BigDecimal;

/**
 * 多乐士涂料
 */
public class DuluxCoat implements IMaterial {
    public String scene() {
        return "涂料";
    }

    public String brand() {
        return "多乐士(Dulux)";
    }

    public String model() {
        return "第二代";
    }

    public BigDecimal price() {
        return new BigDecimal(719);
    }

    public String desc() {
        return "多乐士是阿克苏诺贝尔旗下的著名建筑装饰油漆品牌，产品畅销于全球100个国家，每年全球有5000万户家庭使用多乐士油漆。";
    }
}
