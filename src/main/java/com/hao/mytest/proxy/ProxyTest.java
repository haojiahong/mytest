package com.hao.mytest.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by haojiahong on 16/9/28.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//        Hello proxyHello = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(), dynamicProxy);
//        Hello proxyHello = dynamicProxy.getProxy();
        Hello cgLibProxyHello = new CGLibProxy().getProxy(HelloImpl.class);
        cgLibProxyHello.say();
    }
}
