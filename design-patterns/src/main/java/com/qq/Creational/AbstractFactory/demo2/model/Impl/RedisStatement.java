package com.qq.Creational.AbstractFactory.demo2.model.Impl;

import com.qq.Creational.AbstractFactory.demo2.model.IStatement;

/**
 * Redis 数据库语句类
 */
public class RedisStatement implements IStatement {
    @Override
    public void state() {
        System.out.println("Redis 执行操作");
    }
}
