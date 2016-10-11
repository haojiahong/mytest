package com.hao.mytest.designpatterns.owndo.singleton;

/**
 * Created by haojiahong on 16/10/10.
 */
public class Singleton {
    private Singleton() {

    }

    //静态内部类中的静态属性，jvm会保证线程安全
    private static class HolderClass {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.singleton;
    }
}
