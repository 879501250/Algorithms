package com.qq.Structural.Proxy.agent;

import java.lang.annotation.*;

/**
 * 模拟 mybatis-spring 中的自定义注解，使用在方法层面
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Select {
    // sql语句
    String value() default "";
}
