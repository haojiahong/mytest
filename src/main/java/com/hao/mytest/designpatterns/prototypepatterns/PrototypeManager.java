package com.hao.mytest.designpatterns.prototypepatterns;

import java.util.HashMap;

/**
 * 原型管理器，使用饿汉单例模式
 * Created by haojiahong on 16/5/11.
 */
public class PrototypeManager {
    private HashMap hm = new HashMap();
    private static PrototypeManager pm = new PrototypeManager();

    private PrototypeManager() {

    }

    public void addOfficialDocument(String key, OfficialDocument officialDocument) {
        hm.put(key, officialDocument);
    }

    //通过浅克隆获取新的公文对象
    public OfficialDocument getOfficialDocument(String key) {
        OfficialDocument document = ((OfficialDocument) hm.get(key)).clone();
        return document;

    }

    public static PrototypeManager getPrototypeManager() {
        return pm;
    }


}
