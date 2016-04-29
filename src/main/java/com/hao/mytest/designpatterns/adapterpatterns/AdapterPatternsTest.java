package com.hao.mytest.designpatterns.adapterpatterns;

/**
 * Created by haojiahong on 16/4/8.
 */
public class AdapterPatternsTest {
    public static void main(String[] args) {
        Target t = new Adaptor(new Adaptee());
        t.operation1();
        t.operation2();
    }
}
