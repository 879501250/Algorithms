package com.qq.Creational.AbstractFactory.demo2.model.Impl;

import com.qq.Creational.AbstractFactory.demo2.model.IStatement;

/**
 * MySQL 数据库语句类
 */
public class MySQLStatement implements IStatement {

    @Override
    public void state() {
        System.out.println("MySQL 执行操作");
    }
}
