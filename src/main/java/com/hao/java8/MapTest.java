package com.hao.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haojiahong on 2017/6/30.
 */
public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            //当键值在map中不存在时,才会执行put操作.
            map.putIfAbsent(i, "val" + i);
        }

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i + 1);
        }

        map.forEach((key, value) -> System.out.println(value));

        map.computeIfPresent(3, (num, val) -> val + num + 1);
        System.out.println(map.get(3)); // val331 函数式接口返回值与原来的value不同,则赋予新值.

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));//false 函数式接口返回值为null,则相应的mapping会被删除


        //当键值在map中不存在时,才会计算函数式接口的值,并设值.
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));
        System.out.println(map.get(23));

        map.computeIfAbsent(3, num -> null);
        System.out.println(map.get(3));//val331

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));//val331

        System.out.println(map.getOrDefault(42, "not found"));  // not found
        System.out.println(map.get(42));//null


    }
}
