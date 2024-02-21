package com.qq.Behavioral.Memento.demo1;

/**
 * 备忘录类（即历史版本），可以设置和获取配置信息。
 */
public class ConfigMemento {
    private ConfigFile configFile;

    public ConfigMemento(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }
}
