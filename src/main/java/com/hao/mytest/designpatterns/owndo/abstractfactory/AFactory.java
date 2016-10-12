package com.hao.mytest.designpatterns.owndo.abstractfactory;

/**
 * Created by haojiahong on 16/10/11.
 */
public class AFactory implements MyFactory {
    @Override
    public MyProduct createProduct() {
        return new AProduct();
    }
}
