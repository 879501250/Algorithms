package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件处理类，内部通过一个 map 维护了不同事件要通知的观察者列表
 */
public class EventManager {
    Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public EventManager(EventType... operations) {
        for (EventType operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    // 事件类型，分为 mq 和短信消息
    public enum EventType {
        MQ, Message
    }

    /**
     * 订阅
     *
     * @param eventType 事件类型
     * @param listener  要通知的观察者
     */
    public void subscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * 取消订阅
     *
     * @param eventType 事件类型
     * @param listener  要通知的观察者
     */
    public void unsubscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /**
     * 通知指定事件类型下的所有观察者
     *
     * @param eventType 事件类型
     * @param result    结果
     */
    public void notify(EventType eventType, LotteryResult result) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
    }
}
