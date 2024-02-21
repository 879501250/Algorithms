package com.qq.Behavioral.TemplateMethod.demo1;

import com.qq.Behavioral.TemplateMethod.demo1.group.JDNetMall;

public class Test {
    /**
     * 测试链接
     * 京东；https://item.jd.com/100008348542.html
     * 淘宝；https://detail.tmall.com/item.htm
     * 当当；http://product.dangdang.com/1509704171.html
     */
    public static void main(String[] args) {
        NetMall netMall = new JDNetMall("1000001", "*******");
        String result = netMall.generateGoodsPoster("https://item.jd.com/100008348542.html");
        System.out.println("测试结果：" + result);
    }
}
