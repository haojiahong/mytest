package com.hao.seckill;

import java.lang.annotation.*;

/**
 * lockedObject是参数级的注解，用于注解商品ID等基本类型的参数
 * Created by haojiahong on 16/10/27.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {

}
