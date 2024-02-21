package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

/**
 * 短信事件监听器
 */
public class MessageEventListener implements EventListener {

    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("给用户 [" + result.getuId() + "] 发送短信通知(短信)：" + result.getMsg());
    }

}
