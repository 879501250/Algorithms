package com.qq.Structural.Proxy;

import com.qq.Structural.Proxy.agent.Select;

/**
 * Dao 接口
 */
public interface IUserDao {
    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);

}
