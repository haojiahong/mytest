package com.hao.mytest.crazyjava;

/**
 * Created by haojiahong on 17/2/21.
 */
public class ATest {

    public static void main(String[] args) {
        final int age = 0;
        A a = new A() {
            public void test() {
                System.out.println(age);
            }
        };

    }

    public void testAa() {
        try {
            String s = new String();

        } finally {
            System.out.println("close");
        }
    }
}
