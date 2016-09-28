package com.hao.mytest.proxy;

/**
 * Created by haojiahong on 16/9/28.
 */
public class HelloImpl implements Hello {
    @Override
    public void say() {
        System.out.println("hello world");
    }
}
