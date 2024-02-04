package com.qq.Creational.Singleton.demo7;

/**
 * Effective Java 作者推荐的枚举单例(线程安全)
 */
public enum GlobalCache {
    INSTANCE;

    public void test() {
        System.out.println("hi~");
    }
}
