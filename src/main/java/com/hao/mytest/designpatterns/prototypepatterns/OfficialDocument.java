package com.hao.mytest.designpatterns.prototypepatterns;

/**
 * Created by haojiahong on 16/5/11.
 */
public interface OfficialDocument extends Cloneable {

    public OfficialDocument clone();

    public void display();

}
