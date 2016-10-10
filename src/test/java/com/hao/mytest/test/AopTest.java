package com.hao.mytest.test;

import com.hao.mytest.proxy.Hello;
import com.hao.mytest.proxy.HelloImpl;
import com.hao.mytest.proxy.aop.HelloAfterAdvice;
import com.hao.mytest.proxy.aop.HelloBeforeAdvice;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by haojiahong on 16/9/28.
 */
public class AopTest {
    @Test
    public void testBeforeAfterInProgram() {
        ProxyFactory proxyFactory = new ProxyFactory();
//        Hello proxyHello = (Hello) proxyFactory.getProxy(HelloImpl.class.getClassLoader());
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new HelloAfterAdvice());
        proxyFactory.addAdvice(new HelloBeforeAdvice());
        Hello proxyHello = (Hello) proxyFactory.getProxy();
        proxyHello.say();
    }
}
