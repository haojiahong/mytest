package com.hao.java8;

/**
 * Created by haojiahong on 2017/6/26.
 */
@FunctionalInterface
public interface Converter<F, T> {

    T convertor(F from);

//    T convertor2(F from);

}
