package com.hao.mytest.executorservice;

/**
 * Created by haojiahong on 16/7/28.
 */
public class DeadThreadMain implements Runnable {
    //造成死锁必须是两个线程对相同的对象实例操作。
    DeadThreadTest deadThreadTest = new DeadThreadTest();
    DeadThreadTest.A a = deadThreadTest.new A();
    DeadThreadTest.B b = deadThreadTest.new B();//别的类调用内部类的写法

    public void testInit() {
        b.bar(a);
    }


    @Override
    public void run() {
        a.foo(b);
    }

    public static void main(String[] args) {

        DeadThreadMain deadThreadMain = new DeadThreadMain();
        Thread thread = new Thread(deadThreadMain);
        //造成死锁：不会进入A的last方法，也不会进入b的last方法
        thread.start();
        deadThreadMain.testInit();

    }
}
