package com.hao.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haojiahong on 2017/6/20.
 */
public class StreamApiTest {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();

        apples.add(new Apple(1, "red", "红富士"));
        apples.add(new Apple(3, "red", "红富士"));

        apples.add(new Apple(2, "red", "山东苹果"));
        apples.add(new Apple(4, "red", "山东苹果"));

        apples.add(new Apple(5, "green", "红富士"));
        apples.add(new Apple(7, "green", "红富士"));

        apples.add(new Apple(6, "green", "山东苹果"));
        apples.add(new Apple(8, "green", "山东苹果"));

        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        //过滤
        List<Apple> redApples = apples.stream().filter((apple) -> apple.getColor().equals("red")).collect(Collectors.toList());
        System.out.println(redApples);

        //限制结果集
        List<Apple> limits = apples.stream().limit(2).collect(Collectors.toList());
        System.out.println(limits);

        //映射
        List<String> appleColor = apples.stream().map(Apple::getColor).collect(Collectors.toList());
        System.out.println(appleColor);

        //遍历
        apples.forEach(apple -> System.out.println(apple.getColor()));


    }

}
