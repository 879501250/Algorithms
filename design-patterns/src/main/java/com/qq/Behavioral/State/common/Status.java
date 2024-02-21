package com.qq.Behavioral.State.common;

/**
 * 活动枚举状态
 */
public enum Status {
    // 创建编辑
    Editing,
    // 待审核
    Check,
    // 审核通过(任务扫描成活动中)
    Pass,
    // 审核拒绝(可以撤审到编辑状态)
    Refuse,
    // 活动中
    Doing,
    // 活动关闭
    Close,
    // 活动开启(任务扫描成活动中)
    Open
}
