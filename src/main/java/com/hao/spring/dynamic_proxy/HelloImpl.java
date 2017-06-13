package com.hao.spring.dynamic_proxy;

/**
 * Created by haojiahong on 17/5/31.
 */
public class HelloImpl implements Hello {

    public void say(String name) {
        System.out.println("你好" + name);
        this.work(name);
    }

    public void work(String name) {
        System.out.println("我是" + name);

    }
}
