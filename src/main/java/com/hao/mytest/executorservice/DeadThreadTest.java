package com.hao.mytest.executorservice;

/**
 * Created by haojiahong on 16/7/28.
 */
public class DeadThreadTest {
    public class A {
        public synchronized void foo(B b) {
            System.out.println("当前线程名：" + Thread.currentThread().getName() + "进入A实例的foo方法");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.last();
        }

        public synchronized void last() {
            System.out.println("进入A实例的last方法");
        }
    }

    public class B {
        public synchronized void bar(A a) {
            System.out.println("当前线程名：" + Thread.currentThread().getName() + "进入B实例的bar方法");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.last();
        }

        public synchronized void last() {
            System.out.println("进入B实例的last方法");
        }
    }


}

