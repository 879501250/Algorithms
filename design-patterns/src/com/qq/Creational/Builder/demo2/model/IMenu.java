package com.qq.Creational.Builder.demo2.model;

import com.qq.Creational.Builder.common.IMaterial;

/**
 * 装修套餐接口，各种材料如何组装由具体实现类决定
 */
public interface IMenu {
    // 吊顶
    IMenu appendCeiling(IMaterial matter);

    // 涂料
    IMenu appendCoat(IMaterial matter);

    // 地板
    IMenu appendFloor(IMaterial matter);

    // 地砖
    IMenu appendTile(IMaterial matter);

    // 明细
    String getDetail();
}
