package com.qq.Creational.Prototype.demo1;

import com.qq.Creational.Prototype.common.Mail;

import java.util.Date;

/**
 * 邮件控制器
 */
public class MailController {
    /**
     * 向订阅者发送最新活动
     * @param recipient 订阅者
     */
    public void sendLatestActivity(String recipient){
        String content = "";
        // 模拟调用远程接口，获取最新活动
        try {
            System.out.println("正在获取最新活动。。。");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mail mail = new Mail(recipient, new Date(), content);
        // 发送邮件
        System.out.println("发送邮件成功。。。");
    }
}
