package com.qq.Behavioral.State.demo1;

import com.qq.Behavioral.State.common.ActivityService;
import com.qq.Behavioral.State.common.Status;

public class Test {
    public static void main(String[] args) {
        // 初始化数据
        String activityId = "100001";
        ActivityService.create(activityId, Status.Editing);

        ActivityExecStatusController activityExecStatusController = new ActivityExecStatusController();
        String resultRefuse = activityExecStatusController.execStatus(activityId, Status.Editing, Status.Refuse);
        System.out.println("测试结果(编辑中To审核拒绝)" + resultRefuse);

        String resultCheck = activityExecStatusController.execStatus(activityId, Status.Editing, Status.Check);
        System.out.println("测试结果(编辑中To提交审核)：" + resultCheck);
    }
}
