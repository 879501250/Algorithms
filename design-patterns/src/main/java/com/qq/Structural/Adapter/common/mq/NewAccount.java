package com.qq.Structural.Adapter.common.mq;

import java.util.Date;

/**
 * 新建账户的 MQ 消息格式
 */
public class NewAccount {
    // 开户编号
    private String number;
    // 开户地
    private String address;
    // 开户时间
    private Date accountDate;
    // 开户描述
    private String desc;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
