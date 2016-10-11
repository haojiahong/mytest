package com.hao.mytest.designpatterns.owndo.singleton;

/**
 * Created by haojiahong on 16/10/10.
 */
public class LazySingleton {
    //volatile防止jvm的优化
    private volatile static LazySingleton lazySingleton = null;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            //锁对象没想到
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    return new LazySingleton();
                }
            }

        }
        return lazySingleton;
    }
}
