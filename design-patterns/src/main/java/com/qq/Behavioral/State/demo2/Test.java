package com.qq.Behavioral.State.demo2;

import com.qq.Behavioral.State.common.ActivityInfo;
import com.qq.Behavioral.State.common.ActivityService;
import com.qq.Behavioral.State.common.Status;

public class Test {
    public static void main(String[] args) {
        test_Editing2Arraignment();
        test_Editing2Open();
        test_Refuse2Doing();
        test_Refuse2Revoke();
    }

    public static void test_Editing2Arraignment() {
        String activityId = "100001";
        ActivityService.create(activityId, Status.Editing);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.arraignment(activityId, Status.Editing);
        System.out.println("测试结果(编辑中To提审活动)：" + result);
        ActivityInfo activityInfo = ActivityService.queryActivityInfo(activityId);
        System.out.println("活动信息：[" + activityInfo + "] 状态：[" + activityInfo.getStatus() + "]");
    }

    public static void test_Editing2Open() {
        String activityId = "100001";
        ActivityService.create(activityId, Status.Editing);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.open(activityId, Status.Editing);
        System.out.println("测试结果(编辑中To开启活动)：" + result);
        ActivityInfo activityInfo = ActivityService.queryActivityInfo(activityId);
        System.out.println("活动信息：[" + activityInfo + "] 状态：[" + activityInfo.getStatus() + "]");
    }

    public static void test_Refuse2Doing() {
        String activityId = "100001";
        ActivityService.create(activityId, Status.Refuse);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.doing(activityId, Status.Refuse);
        System.out.println("测试结果(拒绝To活动中))：" + result);
        ActivityInfo activityInfo = ActivityService.queryActivityInfo(activityId);
        System.out.println("活动信息：[" + activityInfo + "] 状态：[" + activityInfo.getStatus() + "]");
    }

    public static void test_Refuse2Revoke() {
        String activityId = "100001";
        ActivityService.create(activityId, Status.Refuse);
        StateHandler stateHandler = new StateHandler();
        String result = stateHandler.checkRevoke(activityId, Status.Refuse);
        System.out.println("测试结果(拒绝To撤审))：" + result);
        ActivityInfo activityInfo = ActivityService.queryActivityInfo(activityId);
        System.out.println("活动信息：[" + activityInfo + "] 状态：[" + activityInfo.getStatus() + "]");
    }
}
