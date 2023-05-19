package com.xxxx.seckill.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {

    //时间
    int second();

    //时间内的限制
    int maxCount();

    //是否登录
    boolean needLogin() default true;
}
