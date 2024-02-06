package com.qq.Creational.Prototype.dmo2;

import com.qq.Creational.Prototype.common.Mail;

import java.util.Date;

public class MailController {
    // 邮件原型
    private static Mail mail = null;

    /**
     * 向订阅者发送最新活动
     *
     * @param recipient 订阅者
     */
    public void sendLatestActivity(String recipient) {
        // 判断邮件内容是否最新，如果不是再调用远程接口获取
        if (!new Date().equals(mail.getDate())) {
            // 模拟调用远程接口，获取最新活动
            try {
                System.out.println("正在获取最新活动。。。");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            // 根据原型克隆一个邮件对象
            Mail clone = (Mail) mail.clone();
            // 设置收件人
            clone.setRecipient(recipient);
            // 发送邮件
            System.out.println("发送邮件成功。。。");
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
