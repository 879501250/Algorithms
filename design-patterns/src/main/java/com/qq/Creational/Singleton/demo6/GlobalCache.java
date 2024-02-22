package com.qq.Creational.Singleton.demo6;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 双重锁校验保证线程安全，实例要使用 volatile 修饰，保证线程安全
 */
public class GlobalCache {
    private static volatile Map<String, Object> instance;

    private GlobalCache() {
    }

    public Map getInstance() {
        if (instance == null) {
            synchronized (GlobalCache.class) {
                if (instance == null) {
                    instance = new HashMap<>();
                }
            }
        }
        return instance;
    }
}
