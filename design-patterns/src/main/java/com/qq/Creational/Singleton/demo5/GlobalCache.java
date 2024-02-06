package com.qq.Creational.Singleton.demo5;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过 java 并发库实现线程安全
 */
public class GlobalCache {
    private static final AtomicReference<GlobalCache> INSTANCE = new AtomicReference<>();

    private GlobalCache() {
    }

    public static final GlobalCache getInstance() {
        while (true) {
            GlobalCache instance = INSTANCE.get();
            if (null != instance) return instance;
            INSTANCE.compareAndSet(null, new GlobalCache());
            return INSTANCE.get();
        }
    }
}
