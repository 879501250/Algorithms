package com.qq.Creational.AbstractFactory.demo2.factory.Impl;

import com.qq.Creational.AbstractFactory.demo2.factory.IDBFactory;
import com.qq.Creational.AbstractFactory.demo2.model.IConnection;
import com.qq.Creational.AbstractFactory.demo2.model.IStatement;
import com.qq.Creational.AbstractFactory.demo2.model.Impl.RedisConnection;
import com.qq.Creational.AbstractFactory.demo2.model.Impl.RedisStatement;

/**
 * Redis 数据库工厂
 */
public class RedisFactory implements IDBFactory {
    @Override
    public IConnection createConnection() {
        return new RedisConnection();
    }

    @Override
    public IStatement createStatement() {
        return new RedisStatement();
    }
}
