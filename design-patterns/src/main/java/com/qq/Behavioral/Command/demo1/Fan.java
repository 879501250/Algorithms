package com.qq.Behavioral.Command.demo1;

import com.qq.Behavioral.Command.common.Switchable;

/**
 * 风扇类
 */
public class Fan implements Switchable {
    @Override
    public void on() {
        System.out.println("通电，风扇转动。");
    }

    @Override
    public void off() {
        System.out.println("断电，风扇停止。");
    }
}
