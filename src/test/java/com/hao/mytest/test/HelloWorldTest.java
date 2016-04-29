package com.hao.mytest.test;

import com.hao.mytest.effectivejava.sixteen.InstrumentedSet;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by haojiahong on 16/4/1.
 */
public class HelloWorldTest {

    @Test
    public void testSayHello() {
        Main main = new Main();
        String result = main.sayHello();

        assertEquals("hello maven", result);
    }

    @Test
    public void testInstrumentSet() {
        InstrumentedSet<String> s = new InstrumentedSet<String>(new HashSet<String>());
        s.addAll(Arrays.asList("Snap", "Pop", "Crackle"));
        System.out.printf("s的大小：" + s.getAddCount());
    }
}
