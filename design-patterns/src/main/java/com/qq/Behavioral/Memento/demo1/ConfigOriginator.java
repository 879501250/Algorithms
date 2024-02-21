package com.qq.Behavioral.Memento.demo1;

/**
 * 记录者类，获取和返回备忘录类对象信息
 */
public class ConfigOriginator {
    private ConfigFile configFile;

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }

    /**
     * 保存备忘录的时候会创建一个备忘录信息，并返回回去，交给管理者处理
     * 创建历史版本
     *
     * @return
     */
    public ConfigMemento saveMemento() {
        return new ConfigMemento(configFile);
    }

    /**
     * 获取的之后并不是直接返回，而是把备忘录的信息交给现在的配置文件 this.configFile
     * 即还原到历史版本
     *
     * @param memento
     */
    public void getMemento(ConfigMemento memento) {
        this.configFile = memento.getConfigFile();
    }
}
