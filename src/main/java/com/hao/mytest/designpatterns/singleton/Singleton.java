package com.hao.mytest.designpatterns.singleton;

/**
 * IoDH单例模式实现方式（克服了饿汉和懒汉模式的缺点）
 * Created by haojiahong on 16/5/10.
 */
public class Singleton {
    private Singleton() {

    }

    private static class HolderClass {
        private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.instance;
    }

    public static void main(String[] args) {
        Singleton s1, s2;
        s1 = Singleton.getInstance();
        s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
