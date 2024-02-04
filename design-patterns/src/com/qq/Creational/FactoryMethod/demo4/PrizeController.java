package com.qq.Creational.FactoryMethod.demo4;

import com.qq.Creational.FactoryMethod.demo4.factory.Impl.CardPrizeFactory;
import com.qq.Creational.FactoryMethod.demo4.model.IPrize;

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
     * @param userId 用户id
     * @param extMap 额外的字段，如实物奖品的收获地址等
     */
    public void sendPrice(String userId, Map<String, String> extMap) {
        log.info("奖品发放开始。" + userId);
        try {
            // 创建具体的奖品工厂
            CardPrizeFactory cardPrizeFactory = new CardPrizeFactory();
            // 由奖品工厂创建奖品
            IPrize prize = cardPrizeFactory.getPrize();
            // 发送奖品
            prize.sendPrize(userId, extMap);
            log.info("奖品发放开始。" + userId);
        } catch (Exception e) {
            log.info("奖品发放失败。" + e);
        }
    }
}
