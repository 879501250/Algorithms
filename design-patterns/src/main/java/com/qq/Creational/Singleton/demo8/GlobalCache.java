package com.qq.Creational.Singleton.demo8;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用内部类(线程安全)
 */
public class GlobalCache {
    private static class CACHE {
        private static Map<String, Object> instance = new HashMap<>();
    }

    private GlobalCache() {
    }

    public static Map getInstance() {
        return CACHE.instance;
    }
}
