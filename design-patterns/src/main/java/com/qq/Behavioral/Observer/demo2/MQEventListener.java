package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

/**
 * 发送 MQ 事件监听器
 */
public class MQEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("记录用户 [" + result.getuId() + "] 摇号结果(MQ)：" + result.getMsg());
    }

}
