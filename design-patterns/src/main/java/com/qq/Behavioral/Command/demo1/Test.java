package com.qq.Behavioral.Command.demo1;

public class Test {
    public static void main(String[] args) {
        System.out.println("===客户端用【电线】直接操作灯泡===");
        Bulb bulb = new Bulb();
        bulb.on();
        bulb.off();
    }
}
