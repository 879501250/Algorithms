package com.qq.Creational.FactoryMethod.demo3.model;

import java.util.Map;

/**
 * 奖品接口，不同奖品有各自的实现类
 */
public interface IPrize {
    /**
     * 发送奖品
     *
     * @param userId 用户 id
     * @param extMap 扩展字段，如实物奖品的收获地址等
     * @throws Exception
     */
    void sendPrize(String userId, Map<String, String> extMap) throws Exception;
}
