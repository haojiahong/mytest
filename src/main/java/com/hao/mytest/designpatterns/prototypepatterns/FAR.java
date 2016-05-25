package com.hao.mytest.designpatterns.prototypepatterns;

/**
 * Created by haojiahong on 16/5/11.
 */
public class FAR implements OfficialDocument {
    @Override
    public OfficialDocument clone() {
        OfficialDocument document = null;
        try {
            document = (OfficialDocument) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public void display() {
        System.out.println("《可行性分析报告》");

    }
}
