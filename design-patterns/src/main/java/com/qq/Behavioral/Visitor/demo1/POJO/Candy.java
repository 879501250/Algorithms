package com.qq.Behavioral.Visitor.demo1.POJO;

import com.qq.Behavioral.Visitor.demo1.Acceptable;
import com.qq.Behavioral.Visitor.demo1.Visitor;

import java.time.LocalDate;

/**
 * 糖果类
 */
public class Candy extends Product implements Acceptable {
    public Candy(String name, LocalDate producedDate, float price) {
        super(name, producedDate, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
