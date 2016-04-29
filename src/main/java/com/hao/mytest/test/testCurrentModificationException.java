package com.hao.mytest.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojiahong on 16/4/12.
 */
public class testCurrentModificationException {
    public static void main(String[] args) {
        List<String> strLs = new ArrayList<String>();
        strLs.add("a");
        strLs.add("B");
        List<String> strLsB = new ArrayList<String>();

        for(String s : strLs){
            System.out.println(s);
            strLsB.add("c");
        }
    }
}
