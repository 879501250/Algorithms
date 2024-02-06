package com.qq.Creational.AbstractFactory.demo2.factory;

import com.qq.Creational.AbstractFactory.demo2.model.IConnection;
import com.qq.Creational.AbstractFactory.demo2.model.IStatement;

/**
 * 数据库工厂接口
 */
public interface IDBFactory {
    IConnection createConnection();
    IStatement createStatement();
}
