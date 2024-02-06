package com.qq.Structural.Adapter.demo2;

import cn.hutool.json.JSONUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * MQ 消息适配器，将不同的 MQ 消息格式转化为一样的格式
 */
public class MQAdapter {
    public static MQInfo filter(String strJson, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return filter(JSONUtil.toBean(strJson, Map.class), link);
    }

    /**
     * 转换 MQ 格式
     *
     * @param obj  原始 MQ 数据
     * @param link 属性映射关系
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static MQInfo filter(Map obj, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MQInfo mqInfo = new MQInfo();
        for (String key : link.keySet()) {
            Object val = obj.get(link.get(key));
            MQInfo.class
                    .getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class)
                    .invoke(mqInfo, val.toString());
        }
        return mqInfo;
    }
}
