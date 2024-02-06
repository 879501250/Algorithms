package com.qq.Creational.AbstractFactory.demo2.model.Impl;

import com.qq.Creational.AbstractFactory.demo2.model.IConnection;

/**
 * MySQL 数据库连接类
 */
public class MySQLConnection implements IConnection {

    @Override
    public void connect() {
        System.out.println("MySQL 正在连接");
    }
}
