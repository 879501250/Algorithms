package com.qq.Behavioral.State.demo2.Impl;

import com.qq.Behavioral.State.common.ActivityService;
import com.qq.Behavioral.State.common.Status;
import com.qq.Behavioral.State.demo2.State;

/**
 * 活动状态；编辑中
 */
public class EditingState extends State {

    public String arraignment(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Check);
        return "活动提审成功";
    }

    public String checkPass(String activityId, Enum<Status> currentStatus) {
        return "编辑中不可审核通过";
    }

    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        return "编辑中不可审核拒绝";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        return "编辑中不可撤销审核";
    }

    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Close);
        return "活动关闭成功";
    }

    public String open(String activityId, Enum<Status> currentStatus) {
        return "非关闭活动不可开启";
    }

    public String doing(String activityId, Enum<Status> currentStatus) {
        return "编辑中活动不可执行活动中变更";
    }

}