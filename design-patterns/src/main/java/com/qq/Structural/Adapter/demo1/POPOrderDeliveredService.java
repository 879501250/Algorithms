package com.qq.Structural.Adapter.demo1;

import cn.hutool.json.JSONUtil;
import com.qq.Structural.Adapter.common.mq.POPOrderDelivered;

/**
 * 处理第三方订单 MQ 消息的处理层
 */
public class POPOrderDeliveredService {
    public void onMessage(String message) {
        // 将 MQ 消息转换为对象进行处理
        POPOrderDelivered mq = JSONUtil.toBean(message, POPOrderDelivered.class);

        mq.getOrderId();
        mq.getuId();

        System.out.println("处理自己的业务...");
    }
}
