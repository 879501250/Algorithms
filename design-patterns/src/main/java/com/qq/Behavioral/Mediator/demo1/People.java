package com.qq.Behavioral.Mediator.demo1;

/**
 * 用户类
 */
public class People {
    private String name;// 用名字来区别人。
    private People other;// 持有对方的引用。

    public String getName() {
        return this.name;
    }

    public People(String name) {
        this.name = name;// 初始化必须起名。
    }

    public void connect(People other) {
        this.other = other;// 连接方法中注入对方引用。
    }

    public void talk(String msg) {
        other.listen(msg);// 我方说话时，对方聆听。
    }

    public void listen(String msg) {
        // 聆听来自对方的声音
        System.out.println(
                other.getName() + " 对 " + this.name + " 说：" + msg
        );
    }
}
