package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

/**
 * 观察者接口，监听到事件后具体执行的逻辑由实现类决定
 */
public interface EventListener {

    // 如果方法的入参信息类型是变化的可以使用泛型 <T>
    void doEvent(LotteryResult result);

}
