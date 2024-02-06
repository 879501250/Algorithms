package com.qq.Creational.AbstractFactory.demo2.factory.Impl;

import com.qq.Creational.AbstractFactory.demo2.factory.IDBFactory;
import com.qq.Creational.AbstractFactory.demo2.model.IConnection;
import com.qq.Creational.AbstractFactory.demo2.model.IStatement;
import com.qq.Creational.AbstractFactory.demo2.model.Impl.MySQLConnection;
import com.qq.Creational.AbstractFactory.demo2.model.Impl.MySQLStatement;

/**
 * MySQL 数据库工厂
 */
public class MySQLFactory implements IDBFactory {
    @Override
    public IConnection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public IStatement createStatement() {
        return new MySQLStatement();
    }
}
