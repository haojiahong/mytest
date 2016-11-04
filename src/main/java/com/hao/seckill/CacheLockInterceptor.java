package com.hao.seckill;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by haojiahong on 16/10/27.
 */
public class CacheLockInterceptor implements InvocationHandler {
    private Object proxied;

    public CacheLockInterceptor(Object proxied) {
        this.proxied = proxied;
    }

    @SuppressWarnings(value = "unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        if (null == cacheLock) {
            System.out.println("no cacheLock annotation");
            method.invoke(proxied, args);
        }
        Annotation[][] annotations = method.getParameterAnnotations();


        return null;
    }

}
