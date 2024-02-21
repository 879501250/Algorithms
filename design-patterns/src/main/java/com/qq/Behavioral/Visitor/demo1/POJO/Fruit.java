package com.qq.Behavioral.Visitor.demo1.POJO;

import com.qq.Behavioral.Visitor.demo1.Acceptable;
import com.qq.Behavioral.Visitor.demo1.Visitor;

import java.time.LocalDate;

/**
 * 水果类，水果需要根据重量结账，因此增加一个重量属性
 */
public class Fruit extends Product implements Acceptable {
    private float weight;

    public Fruit(String name, LocalDate producedDate, float price, float weight) {
        super(name, producedDate, price);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
