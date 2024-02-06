package com.qq.Creational.AbstractFactory.demo1;

/**
 * 数据控制器
 */
public class DataController {

    /**
     * 一次完整的事务操作，其中可能涉及数据库连接和增删改查等操作
     *
     * @param dataBase 需要操作的数据库类型
     */
    public void transaction(String dataBase) {
        if ("MySQL".equals(dataBase)) {
            System.out.println("MySQL 正在连接");
        } else if ("Redis".equals(dataBase)) {
            System.out.println("Redis 正在连接");
        }

        if ("MySQL".equals(dataBase)) {
            System.out.println("MySQL 执行操作");
        } else if ("Redis".equals(dataBase)) {
            System.out.println("Redis 执行操作");
        }
    }
}
