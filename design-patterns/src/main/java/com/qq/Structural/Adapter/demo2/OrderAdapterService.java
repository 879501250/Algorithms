package com.qq.Structural.Adapter.demo2;

/**
 * 统一适配接口
 */
public interface OrderAdapterService {

    /**
     * 判断用户是否是首单
     *
     * @param uId 用户 id
     * @return
     */
    boolean isFirst(String uId);

}
