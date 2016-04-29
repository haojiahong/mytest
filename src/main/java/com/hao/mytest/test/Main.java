package com.hao.mytest.test;

import com.hao.mytest.domain.Employee;

public class Main {
    public String sayHello(){
        return "hello maven";
    }

    public static void main(String[] args) {
        System.out.println(new Main().sayHello());
        Employee e = new Employee();
    }
}
