package com.qq.Creational.AbstractFactory.demo2.model.Impl;

import com.qq.Creational.AbstractFactory.demo2.model.IConnection;

/**
 * Redis 数据库连接类
 */
public class RedisConnection implements IConnection {

    @Override
    public void connect() {
        System.out.println("Redis 正在连接");
    }
}

