package com.hao.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by haojiahong on 2017/6/29.
 */
public class BuiltInFuncTest {

    public static void main(String[] args) {
        //Predicates(断言)
        Predicate<String> predicate = (s -> s.length() > 0);
        System.out.println(predicate.test("foo"));//true
        System.out.println(predicate.negate().test("foo"));//false

        //Functions
        Function<Integer, Integer> time2 = e -> e * 2;
        Function<Integer, Integer> squared = e -> e * e;

        System.out.println(time2.compose(squared).apply(4));//32
        System.out.println(time2.andThen(squared).apply(4));//64

        //Consumers
        Consumer<FuncTest.Person> greeter = person -> System.out.println("hello :" + person.firstName);
        greeter.accept(new FuncTest.Person("jiahong", "hao"));//hello:jiahong

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        //aaa2 aaa1

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        //aaa1 aaa2

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        stringCollection
                .stream()
                .map(String::length)
                .sorted()
                .forEach(System.out::println);


        int max = 1000000;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));



        long tt0 = System.nanoTime();

        long countt = values.parallelStream().sorted().count();
        System.out.println(countt);

        long tt1 = System.nanoTime();

        long millist = TimeUnit.NANOSECONDS.toMillis(tt1 - tt0);
        System.out.println(String.format("parallel sort took: %d ms", millist));




    }
}
