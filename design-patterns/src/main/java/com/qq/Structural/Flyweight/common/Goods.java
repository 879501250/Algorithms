package com.qq.Structural.Flyweight.common;

/**
 * 商品类
 */
public class Goods {
    // 商品 ID
    private Long id;
    // 商品名称
    private String name;
    // 商品描述
    private String desc;
    // 活动库存
    private Stock stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
