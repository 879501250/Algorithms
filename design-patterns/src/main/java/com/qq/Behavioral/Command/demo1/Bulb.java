package com.qq.Behavioral.Command.demo1;

import com.qq.Behavioral.Command.common.Switchable;

/**
 * 灯泡类
 */
public class Bulb implements Switchable {
    @Override
    public void on() {
        System.out.println("通电，灯亮。");
    }

    @Override
    public void off() {
        System.out.println("断电，灯灭。");
    }
}
