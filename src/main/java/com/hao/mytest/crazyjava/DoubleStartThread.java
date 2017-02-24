package com.hao.mytest.crazyjava;

/**
 * Created by haojiahong on 17/2/21.
 */
public class DoubleStartThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        System.out.println("12345");
                    }
                }
        );

        thread.start();
//        thread.start();
//        thread.join();
//        Thread.currentThread().join();
        System.out.println("main 12345");
    }
}
