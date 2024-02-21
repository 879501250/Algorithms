package com.qq.Behavioral.Visitor.demo1;

/**
 * 接待者接口
 */
public interface Acceptable {
    // 主动接受拜访者
    public void accept(Visitor visitor);
}
