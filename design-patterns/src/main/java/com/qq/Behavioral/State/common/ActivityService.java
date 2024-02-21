package com.qq.Behavioral.State.common;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 活动服务接口
 */
public class ActivityService {
    private static Map<String, ActivityInfo> statusMap = new ConcurrentHashMap<>();

    public static void create(String activityId, Enum<Status> status) {
        // 模拟创建一个活动
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activityId);
        activityInfo.setActivityName("早起学习打卡领奖活动");
        activityInfo.setStatus(status);
        activityInfo.setBeginTime(new Date());
        activityInfo.setEndTime(new Date());
        statusMap.put(activityId, activityInfo);
    }

    /**
     * 查询活动信息
     *
     * @param activityId 活动ID
     * @return 查询结果
     */
    public static ActivityInfo queryActivityInfo(String activityId) {
        // 模拟查询活动信息
        return statusMap.get(activityId);
    }

    /**
     * 查询活动状态
     *
     * @param activityId 活动ID
     * @return 查询结果
     */
    public static Enum<Status> queryActivityStatus(String activityId) {
        return statusMap.get(activityId).getStatus();
    }

    /**
     * 执行状态变更
     *
     * @param activityId   活动ID
     * @param beforeStatus 变更前状态
     * @param afterStatus  变更后状态 b
     */
    public static synchronized void execStatus(String activityId, Enum<Status> beforeStatus, Enum<Status> afterStatus) {
        // 如果当前活动状态不匹配，之间返回
        if (!beforeStatus.equals(statusMap.get(activityId)))
            return;
        statusMap.get(activityId).setStatus(afterStatus);
    }
}
