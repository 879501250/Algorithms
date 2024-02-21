package com.qq.Behavioral.Mediator.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室，即中介
 */
public class ChatRoom {
    private String name;// 聊天室命名

    public ChatRoom(String name) {
        this.name = name;// 初始化必须命名聊天室
    }

    List<User> users = new ArrayList<>();// 聊天室里的用户们

    public void connect(User user) {
        this.users.add(user);// 用户进入聊天室加入列表。
        System.out.print("欢迎【");
        System.out.print(user.getName());
        System.out.println("】加入聊天室【" + this.name + "】");
    }

    public void sendMsg(User fromWhom, String msg) {
        // 循环所有用户，只发消息给非发送方fromWhom。
        users.stream()
                .filter(user -> !user.equals(fromWhom))// 过滤掉发送方fromWhom
                .forEach(toWhom -> toWhom.listen(fromWhom, msg));// 发送消息给剩下的所有人
    }
}
