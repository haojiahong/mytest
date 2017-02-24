package com.hao.mytest.crazyjava;

/**
 * Created by haojiahong on 17/2/22.
 */
public class Goods {

    private class Content implements Contents, Destination {
        private int i = 11;


        public int value() {
            return i;
        }

        public String readLabel() {
            return null;
        }
    }


    public static void main(String[] args) {
        String s;
        s = "";
        System.out.println(s);
    }
}
