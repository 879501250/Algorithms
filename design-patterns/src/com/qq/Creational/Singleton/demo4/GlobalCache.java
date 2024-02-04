package com.qq.Creational.Singleton.demo4;

import java.util.HashMap;
import java.util.Map;

/**
 * 恶汉式（线程安全）
 */
public class GlobalCache {
    private static Map<String, Object> instance = new HashMap<>();

    private GlobalCache() {
    }

    public synchronized Map getInstance() {
        return instance;
    }
}
