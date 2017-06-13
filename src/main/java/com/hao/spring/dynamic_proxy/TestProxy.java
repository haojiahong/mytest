package com.hao.spring.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * Created by haojiahong on 17/5/31.
 */
public class TestProxy {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy
        );

        helloProxy.say("Jack");
    }
}
