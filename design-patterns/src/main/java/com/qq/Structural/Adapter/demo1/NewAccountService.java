package com.qq.Structural.Adapter.demo1;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qq.Structural.Adapter.common.mq.NewAccount;

/**
 * 处理新建账户 MQ 消息的处理层
 */
public class NewAccountService {
    public void onMessage(String message) {
        // 将 MQ 消息转换为对象进行处理
        NewAccount mq = JSONUtil.toBean(message, NewAccount.class);

        mq.getNumber();
        mq.getAccountDate();

        System.out.println("处理自己的业务...");
    }
}
