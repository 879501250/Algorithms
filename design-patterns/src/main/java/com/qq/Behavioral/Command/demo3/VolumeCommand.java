package com.qq.Behavioral.Command.demo3;

/**
 * 音量调节命令类
 */
public class VolumeCommand implements Command {

    private Device device;

    public VolumeCommand(Device device) {
        this.device = device;
    }

    @Override
    public void exe() {
        device.volumeUp();
    }

    @Override
    public void unexe() {
        device.volumeDown();
    }

}
