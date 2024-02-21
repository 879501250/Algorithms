package com.qq.Behavioral.Visitor.demo1;

import com.qq.Behavioral.Visitor.demo1.POJO.Candy;
import com.qq.Behavioral.Visitor.demo1.POJO.Fruit;
import com.qq.Behavioral.Visitor.demo1.POJO.Product;
import com.qq.Behavioral.Visitor.demo1.POJO.Wine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 三件商品加入购物车
        List<Acceptable> products = Arrays.asList(
                new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f),
                new Wine("猫泰白酒", LocalDate.of(2017, 1, 1), 1000.00f),
                new Fruit("草莓", LocalDate.of(2018, 12, 26), 10.00f, 2.5f)
        );

        Visitor discountVisitor = new DiscountVisitor(LocalDate.of(2019, 1, 1));
        // 迭代购物车轮流结算
        for (Acceptable product : products) {
            product.accept(discountVisitor);
        }


    }
}
