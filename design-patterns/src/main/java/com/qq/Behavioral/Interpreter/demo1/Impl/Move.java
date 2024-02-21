package com.qq.Behavioral.Interpreter.demo1.Impl;

import com.qq.Behavioral.Interpreter.demo1.Expression;

/**
 * 鼠标移动表达式
 */
public class Move implements Expression {
    // 鼠标位置坐标
    private int x, y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void interpret() {
        System.out.println("移动鼠标：【" + x + "," + y + "】");
    }

}
