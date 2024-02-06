package com.qq.Creational.Singleton.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用静态类
 */
public class GlobalCache {
    public static Map<String,Object> instance = new HashMap<>();
}
