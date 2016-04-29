package com.hao.mytest.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by haojiahong on 16/4/1.
 */
public class HelloWorldTest {

    @Test
    public void testSayHello(){
        Main main = new Main();
        String result = main.sayHello();

        assertEquals("hello maven",result);
    }
}
