package com.qq.Behavioral.Memento.demo1;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Admin admin = new Admin();
        ConfigOriginator configOriginator = new ConfigOriginator();
        configOriginator.setConfigFile(new ConfigFile("1000001", "配置内容A=哈哈", new Date(), "小傅哥"));
        admin.append(configOriginator.saveMemento()); // 保存配置
        configOriginator.setConfigFile(new ConfigFile("1000002", "配置内容A=嘻嘻", new Date(), "小傅哥"));
        admin.append(configOriginator.saveMemento()); // 保存配置
        configOriginator.setConfigFile(new ConfigFile("1000003", "配置内容A=么么", new Date(), "小傅哥"));
        admin.append(configOriginator.saveMemento()); // 保存配置
        configOriginator.setConfigFile(new ConfigFile("1000004", "配置内容A=嘿嘿", new Date(), "小傅哥"));
        admin.append(configOriginator.saveMemento()); // 保存配置

        // 历史配置(回滚)
        configOriginator.getMemento(admin.undo());
        System.out.println("历史配置(回滚)undo：" + configOriginator.getConfigFile());

        // 历史配置(回滚)
        configOriginator.getMemento(admin.undo());
        System.out.println("历史配置(回滚)undo：" + configOriginator.getConfigFile());

        // 历史配置(前进)
        configOriginator.getMemento(admin.redo());
        System.out.println("历史配置(前进)redo：" + configOriginator.getConfigFile());

        // 历史配置(获取)
        configOriginator.getMemento(admin.get("1000002"));
        System.out.println("历史配置(获取)get：" + configOriginator.getConfigFile());
    }
}
