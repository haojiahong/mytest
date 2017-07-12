package com.hao.java8;

/**
 * Created by haojiahong on 2017/6/27.
 */
public interface Formula {

    double calculate(int a);

    //AVA8中可以通过default关键字在接口中添加一个非抽象方法（即：扩展方法)
    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}
