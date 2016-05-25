package com.hao.mytest.test;

import com.hao.mytest.effectivejava.sixteen.InstrumentedSet;
import com.hao.mytest.effectivejava.thirty.Operation;
import com.hao.mytest.effectivejava.twentysix.StackGeneric;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    @Test
    public void testGeneric() {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, new Integer(24).toString());
        String s = strings.get(0);
        System.out.printf(s);

    }

    private void unsafeAdd(List<String> list, String object) {
        list.add(object);
    }

    public void testArrayGeneric() {
//        List<String>[] stringLists = new List<String>[1];
        List<String> stringList = new ArrayList<String>();
//        stringList.add(new Integer(2));

    }

    public void wildcard(List<? extends Number> list) {
//        list.add(new Integer(1));
    }

    @Test
    public void testStackGeneric() {
        StackGeneric<String> stackGeneric = new StackGeneric<String>();
        stackGeneric.push("haojiahong");
        stackGeneric.push("hello world");
        while (!stackGeneric.isEmpty()) {
            System.out.printf(stackGeneric.pop().toUpperCase() + "%n");
        }
    }

    @Test
    public void testOperation() {

        double x = Double.parseDouble("1");
        double y = Double.parseDouble("2");
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
        System.out.printf("" + Operation.PLUS.apply(x, y));
    }
}
