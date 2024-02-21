package com.qq.Behavioral.Interpreter.demo1.Impl;

import com.qq.Behavioral.Interpreter.demo1.Expression;

public class LeftDown implements Expression {
    @Override
    public void interpret() {
        System.out.println("按下鼠标：左键");
    }
}
