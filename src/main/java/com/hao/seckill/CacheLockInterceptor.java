package com.hao.seckill;

import com.hao.util.SpringIocUtil;
import com.hao.util.lock.RedisLock;
import com.hao.util.lock.RedisLockFactory;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by haojiahong on 16/10/27.
 */
public class CacheLockInterceptor implements InvocationHandler {
    private Object proxied;
    private RedisLockFactory redisLockFactory;

    public CacheLockInterceptor(Object proxied) {
        this.proxied = proxied;
        this.redisLockFactory = (RedisLockFactory) SpringIocUtil.getBean("redisLockFactory");
    }

    //想想这个用spring aop 配置一下，看combo代码里的容灾怎么获取spring容器bean
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
        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();
        //根据获取到的参数注解和参数列表获得加锁的参数
        Object lockedObject = getLockedObject(annotations, args);
        String objectValue = lockedObject.toString();
//        RedisLock redisLock = new RedisLock(objectValue);
        RedisLock lock = redisLockFactory.newRedisLock(objectValue);
        boolean result = lock.lock();
        if (!result) {//取锁失败
            throw new CacheLockException("get lock fail");
        }
        try {
            return method.invoke(proxied, args);
        } finally {
            lock.unlock();
        }

    }

    /**
     * 从方法参数中找出@lockedComplexOnbject的参数，在redis中取该参数对应的锁
     *
     * @param annotations
     * @param args
     * @return
     * @throws CacheLockException
     */
    private Object getLockedObject(Annotation[][] annotations, Object[] args) throws CacheLockException {
        if (null == args || args.length == 0) {
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }

        if (null == annotations || annotations.length == 0) {
            throw new CacheLockException("没有被注解的参数");
        }
        //不支持多个参数加锁，只支持第一个注解为lockedObject或者lockedComplexObject的参数
        int index = -1;//标记参数的位置指针
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedComplexObject) {//注解为LockedComplexObject
                    //index = i;
                    try {
                        return args[i].getClass().getField(((LockedComplexObject) annotations[i][j]).field());
                    } catch (NoSuchFieldException | SecurityException e) {
                        throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject) annotations[i][j]).field());
                    }
                }

                if (annotations[i][j] instanceof LockedObject) {
                    index = i;
                    break;
                }
            }
            //找到第一个后直接break，不支持多参数加锁
            if (index != -1) {
                break;
            }
        }

        if (index == -1) {
            throw new CacheLockException("请指定被锁定参数");
        }

        return args[index];
    }

}
