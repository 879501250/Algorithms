package com.qq.Creational.Builder.common;

import java.math.BigDecimal;

/**
 * 装修材料接口
 */
public interface IMaterial {
    // 场景，如地板、地砖、涂料、吊顶等
    String scene();

    // 品牌
    String brand();

    // 型号
    String model();

    // 价格
    BigDecimal price();

    // 描述
    String desc();
}
