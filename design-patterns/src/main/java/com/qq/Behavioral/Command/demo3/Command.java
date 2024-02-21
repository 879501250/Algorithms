package com.qq.Behavioral.Command.demo3;

/**
 * 命令模块
 */
public interface Command {
    // 执行命令操作
    public void exe();

    // 反执行命令操作
    public void unexe();
}
