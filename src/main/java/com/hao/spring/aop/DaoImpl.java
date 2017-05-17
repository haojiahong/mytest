package com.hao.spring.aop;

/**
 * Created by haojiahong on 17/5/16.
 */
public class DaoImpl implements Dao {

    public void select() {
        System.out.println("Enter DaoImpl.select()");

    }

    public void insert() {
        System.out.println("Enter DaoImpl.insert()");
        this.select();

    }
}
