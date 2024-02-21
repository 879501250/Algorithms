package com.qq.Behavioral.Observer.demo1;

import com.qq.Behavioral.Observer.common.LotteryResult;
import com.qq.Behavioral.Observer.common.MinibusTargetService;

import java.util.Date;

public class LotteryServiceImpl implements LotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    public LotteryResult doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 发短信
        System.out.println("给用户 [" + uId + "] 发送短信通知(短信)：" + lottery);
        // 发MQ消息
        System.out.println("记录用户 [" + uId + "] 摇号结果(MQ)：" + lottery);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }
}
