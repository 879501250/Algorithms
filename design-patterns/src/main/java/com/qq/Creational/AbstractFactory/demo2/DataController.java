package com.qq.Creational.AbstractFactory.demo2;

import com.qq.Creational.AbstractFactory.demo2.factory.IDBFactory;
import com.qq.Creational.AbstractFactory.demo2.factory.Impl.MySQLFactory;
import com.qq.Creational.AbstractFactory.demo2.model.IConnection;
import com.qq.Creational.AbstractFactory.demo2.model.IStatement;

/**
 * 数据控制器
 */
public class DataController {

    /**
     * 一次完整的事务操作，其中可能涉及数据库连接和增删改查等操作
     */
    public void transaction() {
        try {
            IDBFactory factory;
            IConnection connection;
            IStatement statement;

            factory = new MySQLFactory();
            connection = factory.createConnection();
            connection.connect();

            statement = factory.createStatement();
            statement.state();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
