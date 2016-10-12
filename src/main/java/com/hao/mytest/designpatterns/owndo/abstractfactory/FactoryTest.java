package com.hao.mytest.designpatterns.owndo.abstractfactory;

/**
 * Created by haojiahong on 16/10/11.
 */
public class FactoryTest {
    public static void main(String[] args) {
        MyFactory factory = new AFactory();
        MyProduct product = factory.createProduct();
    }
}
