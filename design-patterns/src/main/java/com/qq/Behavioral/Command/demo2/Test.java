package com.qq.Behavioral.Command.demo2;

import com.qq.Behavioral.Command.demo1.Bulb;
import com.qq.Behavioral.Command.demo1.Fan;

public class Test {
    public static void main(String[] args) {
        System.out.println("===客户端用【开关】操作电器===");
        Switcher switcher = new Switcher();

        switcher.setSwitchable(new Bulb());// 灯泡接入开关。
        switcher.buttonOnClick();
        switcher.buttonOffClick();
        switcher.setSwitchable(new Fan());// 风扇接入开关。
        switcher.buttonOnClick();
        switcher.buttonOffClick();
    }
}
