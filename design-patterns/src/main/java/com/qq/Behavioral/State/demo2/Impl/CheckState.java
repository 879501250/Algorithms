package com.qq.Behavioral.State.demo2.Impl;

import com.qq.Behavioral.State.common.ActivityService;
import com.qq.Behavioral.State.common.Status;
import com.qq.Behavioral.State.demo2.State;

/**
 * 活动状态；待审核
 */
public class CheckState extends State {

    public String arraignment(String activityId, Enum<Status> currentStatus) {
        return "待审核状态不可重复提审";
    }

    public String checkPass(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Pass);
        return "活动审核通过完成";
    }

    public String checkRefuse(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Refuse);
        return "活动审核拒绝完成";
    }

    @Override
    public String checkRevoke(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Editing);
        return "活动审核撤销回到编辑中";
    }

    public String close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.Close);
        return "活动审核关闭完成";
    }

    public String open(String activityId, Enum<Status> currentStatus) {
        return "非关闭活动不可开启";
    }

    public String doing(String activityId, Enum<Status> currentStatus) {
        return "待审核活动不可执行活动中变更";
    }

}
