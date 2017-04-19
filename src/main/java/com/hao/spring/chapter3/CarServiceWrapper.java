package com.hao.spring.chapter3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by haojiahong on 17/4/11.
 */
public class CarServiceWrapper implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("car invoke before...");
        System.out.println("methodName=" + invocation.getMethod().getName());
        Object o = invocation.proceed();
        System.out.println("car invoke after...");
        return o;
    }
}
