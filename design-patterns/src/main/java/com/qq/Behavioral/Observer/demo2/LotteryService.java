package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

/**
 * 业务抽象类接口
 */
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        // 初始化时添加 mq 和短信消息两种类型的观察者，方便后续通知
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        // 需要什么通知就给调用什么方法
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.Message, lotteryResult);
        return lotteryResult;
    }

    // 具体业务的核心流程
    protected abstract LotteryResult doDraw(String uId);

}
