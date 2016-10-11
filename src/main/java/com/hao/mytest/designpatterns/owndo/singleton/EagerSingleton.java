package com.hao.mytest.designpatterns.owndo.singleton;

/**
 * Created by haojiahong on 16/10/10.
 */
public class EagerSingleton {
    //一开始final忘记写了
    private static final EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}
