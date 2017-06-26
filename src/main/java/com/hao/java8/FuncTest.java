package com.hao.java8;

/**
 * Created by haojiahong on 2017/6/26.
 */
public class FuncTest {

    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convertor("8");
        System.out.println(converted);


        Converter<String, Integer> staticFunConverter = Integer::valueOf;
        Integer staticFunConverted = staticFunConverter.convertor("123");
        System.out.println(staticFunConverted);   // 123

        Something something = new Something();
        Converter<String, String> objFunConverter = something::startsWith;
        String objFunConverted = objFunConverter.convertor("Java");
        System.out.println(objFunConverted);//J

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("pee", "hao");
        System.out.println(person.lastName);//hao



    }

    static class Person {
        String firstName;
        String lastName;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

}
