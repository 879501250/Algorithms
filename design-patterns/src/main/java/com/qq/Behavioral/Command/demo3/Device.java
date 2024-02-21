package com.qq.Behavioral.Command.demo3;

import com.qq.Behavioral.Command.common.Switchable;

/**
 * 高级设备接口
 */
public interface Device extends Switchable {
    // 频道+
    public void channelUp();

    // 频道-
    public void channelDown();

    // 音量+
    public void volumeUp();

    // 音量-
    public void volumeDown();
}
