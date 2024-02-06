package com.qq.Creational.FactoryMethod.demo2;

import com.qq.Creational.FactoryMethod.demo2.factory.PrizeFactory;
import com.qq.Creational.FactoryMethod.demo2.model.IPrize;

import java.util.Map;
import java.util.logging.Logger;

/**
 * 奖品控制器
 */
public class PrizeController {
    private final static Logger log = Logger.getLogger(PrizeController.class.getName());

    /**
     * 发送奖品
     *
     * @param userId    用户id
     * @param prizeType 奖品类型
     * @param extMap    额外的字段，如实物奖品的收获地址等
     */
    public void sendPrice(String userId, int prizeType, Map<String, String> extMap) {
        log.info("奖品发放开始。" + userId);
        try {
            // 根据奖品类型从工厂里获取不同奖品
            IPrize prize = new PrizeFactory().getPrize(prizeType);
            prize.sendPrize(userId, extMap);
            log.info("奖品发放开始。" + userId);
        } catch (Exception e) {
            log.info("奖品发放失败。" + e);
        }
    }
}
