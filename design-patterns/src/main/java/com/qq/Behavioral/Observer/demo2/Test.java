package com.qq.Behavioral.Observer.demo2;

import com.qq.Behavioral.Observer.common.LotteryResult;

public class Test {
    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.doDraw("2765789109876");
        System.out.println("测试结果：" + result);
    }
}
