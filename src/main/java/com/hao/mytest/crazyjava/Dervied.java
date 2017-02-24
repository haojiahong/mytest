package com.hao.mytest.crazyjava;

/**
 * Created by haojiahong on 17/2/22.
 */
public class Dervied extends Base {
    private String name = "dervied";

    public Dervied() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }

    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args) {

        new Dervied();
    }
}
