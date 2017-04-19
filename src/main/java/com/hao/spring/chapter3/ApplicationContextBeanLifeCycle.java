package com.hao.spring.chapter3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by haojiahong on 17/4/10.
 */
public class ApplicationContextBeanLifeCycle {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
        Car car1 = ctx.getBean("car", Car.class);
        Car car2 = ctx.getBean("car", Car.class);
        System.out.println(car1 == car2);
        car1.introduce();
        ((AbstractApplicationContext) ctx).registerShutdownHook();
    }
}
