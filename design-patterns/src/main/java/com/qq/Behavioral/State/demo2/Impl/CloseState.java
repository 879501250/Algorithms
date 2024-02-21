package com.qq.Behavioral.State.demo2.Impl;

import com.qq.Behavioral.State.common.ActivityService;
import com.qq.Behavioral.State.common.Status;
import com.qq.Behavioral.State.demo2.State;

/**
 * 活动状态；活动关闭
 */
public class CloseState extends State {

    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可提审";
    }

    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可审核通过";
    }

    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可审核拒绝";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可撤销审核";
    }

    public String close(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可重复关闭";
    }

    public String open(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Open);
        return "活动开启完成";
    }

    public String doing(String activityId, Enum<Status> currentStatus) {
        return "活动关闭不可变更活动中";
    }

}