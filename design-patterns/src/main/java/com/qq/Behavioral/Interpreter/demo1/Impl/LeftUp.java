package com.qq.Behavioral.Interpreter.demo1.Impl;

import com.qq.Behavioral.Interpreter.demo1.Expression;

public class LeftUp implements Expression {
    @Override
    public void interpret() {
        System.out.println("松开鼠标：左键");
    }
}
