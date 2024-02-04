package com.qq.Creational.Prototype.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Mail implements Cloneable {
    // 收件人
    private String recipient;
    // 日期
    private Date date;
    // 邮件内容
    private String content;

    // 构造邮件
    public Mail(String recipient, Date date, String content) {
        this.recipient = recipient;
        this.date = date;
        this.content = content;
        // 模拟创建邮件比较耗时
        try {
            System.out.println("正在构建邮件。。。");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Mail mail = (Mail) super.clone();
        return mail;
    }
}
