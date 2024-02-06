package com.qq.Structural.Adapter.demo2;

import java.util.Date;

/**
 * 统一的 MQ 消息格式
 */
public class MQInfo {
    // 用户ID
    private String userId;
    // 业务ID
    private String bizId;
    // 业务时间
    private Date bizTime;
    // 业务描述
    private String desc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(Date bizTime) {
        this.bizTime = bizTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
