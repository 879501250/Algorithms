package com.qq.Creational.FactoryMethod.demo1;

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
            // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
            if (prizeType == 1) {
                System.out.println("创建优惠券");
                System.out.println("调用发送优惠卷的逻辑");
            } else if (prizeType == 2) {
                System.out.println("创建实物商品");
                System.out.println("调用发送实物商品的逻辑");
            } else if (prizeType == 3) {
                System.out.println("创建第三方兑换卡");
                System.out.println("调用发送第三方兑换卡的逻辑");
            }
            log.info("奖品发放开始。" + userId);
        } catch (Exception e) {
            log.info("奖品发放失败。" + e);
        }
    }
}
