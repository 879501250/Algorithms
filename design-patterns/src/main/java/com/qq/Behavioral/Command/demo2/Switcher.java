package com.qq.Behavioral.Command.demo2;

import com.qq.Behavioral.Command.common.Switchable;

/**
 * 开关控制器
 */
public class Switcher {
    // 此开关与灯耦合，无法替换为其他电器。
    // private Bulb bulb = new Bulb();

    // 此开关与电器接口耦合，可任意替换电器。
    private Switchable switchable;

    // 替换电器方法
    public void setSwitchable(Switchable switchable) {
        this.switchable = switchable;
    }

    // 按键事件绑定

    // 按钮“开”按下
    public void buttonOnClick() {
        System.out.println("按下开……");
        switchable.on();
    }

    // 按钮“关”按下
    public void buttonOffClick() {
        System.out.println("按下关……");
        switchable.off();
    }

}
