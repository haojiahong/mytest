package com.hao.mytest.designpatterns.adapterpatterns;

/**
 * Created by haojiahong on 16/4/8.
 */
public class Adaptor implements Target {
    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void operation1() {
        adaptee.operation1();
    }

    @Override
    public void operation2() {
        System.out.println("run operation2");
    }
}
