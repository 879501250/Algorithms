package com.qq.Behavioral.Interpreter.demo1.Impl;

import com.qq.Behavioral.Interpreter.demo1.Expression;

import java.util.List;

/**
 * 指令序列，内部包含所有指令，依次执行
 */
public class Sequence implements Expression {
    // 指令集序列
    private List<Expression> expressions;

    public Sequence(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void interpret() {
        // 循环挨个解析每条指令
        expressions.forEach(exp -> exp.interpret());
    }
}
