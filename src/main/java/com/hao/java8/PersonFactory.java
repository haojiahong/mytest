package com.hao.java8;

/**
 * Created by haojiahong on 2017/6/26.
 */
public interface PersonFactory<P extends FuncTest.Person> {

    P create(String firstName, String lastName);

}
