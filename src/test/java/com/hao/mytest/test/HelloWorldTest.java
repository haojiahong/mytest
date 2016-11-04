package com.hao.mytest.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hao.mytest.effectivejava.sixteen.InstrumentedSet;
import com.hao.mytest.effectivejava.thirty.Operation;
import com.hao.mytest.effectivejava.twentysix.StackGeneric;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

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

    @Test
    public void testNormal() {
        Integer i = null;
        doNormal(i);

    }

    private void doNormal(int i) {
        System.out.println(i);
    }


    @Test
    public void testFixedSizeTest() {
        List fixedSizeList = Arrays.asList("haojiahong", "haojiahong2");
        System.out.println(fixedSizeList.getClass());
        for (Object o : fixedSizeList) {
            System.out.println(o);
        }
        fixedSizeList.add("name");//报错

    }

    @Test
    public void testLinkedList() {
        LinkedList<String> books = new LinkedList<String>();
        books.offer("hao1");
        books.offer("hao2");
        books.push("hao3");
        books.offerFirst("hao4");
        for (String book : books) {
            System.out.println(book);
        }
        System.out.println("-------");
        System.out.println(books.peekLast());
    }

    @Test
    public void testEntity() {
        TestEntity testEntity = new TestEntity();//加强型会是null 但基本类型是有默认值的。
        System.out.println(testEntity.toString());
    }

    @Test
    public void testIsEmpty() {
        List<Integer> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(result)) {
            System.out.println("new出来的集合，没有值，也是空的");
        } else {
            System.out.println("new出来的集合不算空的");
        }
    }

    @Test
    public void testCalendar() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date startTime = todayStart.getTime();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
    }

    @Test
    public void testCalendar2() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
    }

    @Test
    public void testJson() {
        Map<String, String> map = Maps.newHashMap();
        map.put("aaa", "aaaa");
        map.put("bbb", "bbbb");
        String result = JSON.toJSONString(map);
        System.out.println(result);
    }

    @Test
    public void testCollection() {
        List<Integer> result = Lists.newArrayList();
        boolean flag = CollectionUtils.isEmpty(result);
        System.out.println(flag);

    }

    @Test
    public void testNumbers() {
        class Result {
            private BigDecimal value;

            public void setValue(BigDecimal value) {
                this.value = value;
            }

            public BigDecimal getValue() {
                return this.value;
            }
        }
        BigDecimal m = BigDecimal.ONE;
        Result result = new Result();
        result.setValue(m);
        for (int i = 0; i < 10; i++) {
            m = m.add(BigDecimal.TEN);
        }
        System.out.println(result.getValue());
    }

    @Test
    public void testCompareNum() {
        Integer i = 1;
        Integer j = null;
        if (i < j) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }


    @Test
    public void testGitVersion() {
        System.out.println("版本回退真是蛋疼");
        System.out.println("版本回退真是蛋疼2");
        System.out.println("版本回退真是蛋疼3");
    }

    @Test
    public void testCastLong() {
        List<Long> longList = Lists.newArrayList(1l, 2l, 3l);
        List<Integer> integerList = Lists.newArrayList();
        for (Long id : longList) {
            integerList.add(id.intValue());
        }
        System.out.println(JSON.toJSONString(integerList));
    }

    @Test
    public void testBigDecimal() {
        BigDecimal a = new BigDecimal(3625);
        BigDecimal b = new BigDecimal(100);
        BigDecimal c = a.divide(b, BigDecimal.ROUND_DOWN);
        BigDecimal total = new BigDecimal(4240);
        BigDecimal targetValue = new BigDecimal(1000);
        BigDecimal num = total.divide(targetValue, BigDecimal.ROUND_DOWN);

        System.out.println(c.toString());
    }

    @Test
    public void testBigDecimal2() {
        BigDecimal a = new BigDecimal(3625);
        BigDecimal b = new BigDecimal(100);
        BigDecimal c = a.divideToIntegralValue(b);
        System.out.println(c.toString());
    }

    @Test
    public void testUnion() {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list1.add("abc");
        list2.add("abc");
        list1.add("123");
        list2.add("123");
        list1.add("ABC");
        list2.add("XYZ");
        list1.retainAll(list2);
        System.out.println(list1.toString());
        System.out.println("交集元素个数是：" + list1.size());
    }

    @Test
    public void testObject() {
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        Object o = integerList;
        List<Integer> result = (List<Integer>) o;
        System.out.println(result.get(0));

    }


}
