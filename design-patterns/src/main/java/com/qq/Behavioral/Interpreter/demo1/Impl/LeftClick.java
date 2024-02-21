package com.qq.Behavioral.Interpreter.demo1.Impl;

import com.qq.Behavioral.Interpreter.demo1.Expression;

/**
 * 单击操作表达式
 */
public class LeftClick implements Expression {
    private Expression leftDown;
    private Expression leftUp;

    public LeftClick() {
        this.leftDown = new LeftDown();
        this.leftUp = new LeftUp();
    }

    @Override
    public void interpret() {
        // 单击=先按下再松开
        leftDown.interpret();
        leftUp.interpret();
    }
}
