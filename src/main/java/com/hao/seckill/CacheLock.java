package com.hao.seckill;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cachelock是方法级的注解，用于注解会产生并发问题的方法
 * Created by haojiahong on 16/10/27.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheLock {
    String lockedPrefix() default "";//redis 锁key的前缀

    long timeOut() default 2000;//轮询锁的时间

    int expireTime() default 1000;//key在redis里存在的时间，1000S
}
