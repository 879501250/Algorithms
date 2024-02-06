package com.qq.Structural.Adapter.demo1;

import cn.hutool.json.JSONUtil;
import com.qq.Structural.Adapter.common.mq.OrderMq;

/**
 * 处理内部订单 MQ 消息的处理层
 */
public class OrderMqService {
    public void onMessage(String message) {
        // 将 MQ 消息转换为对象进行处理
        OrderMq mq = JSONUtil.toBean(message, OrderMq.class);

        mq.getOrderId();
        mq.getUid();

        System.out.println("处理自己的业务...");
    }
}
