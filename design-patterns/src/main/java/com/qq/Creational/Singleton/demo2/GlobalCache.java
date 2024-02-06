package com.qq.Creational.Singleton.demo2;

import java.util.HashMap;
import java.util.Map;

/**
 * 懒汉式（线程不安全）
 */
public class GlobalCache {
    private static Map<String, Object> instance;

    private GlobalCache() {
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new HashMap<>();
        }
        return instance;
    }
}
