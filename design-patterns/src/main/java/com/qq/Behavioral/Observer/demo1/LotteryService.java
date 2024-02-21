package com.qq.Behavioral.Observer.demo1;

import com.qq.Behavioral.Observer.common.LotteryResult;

public interface LotteryService {
    LotteryResult doDraw(String uId);
}
