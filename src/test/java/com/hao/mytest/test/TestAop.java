package com.hao.mytest.test;

import com.hao.spring.aop.Dao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by haojiahong on 17/5/16.
 */
public class TestAop {
    @Test
    public void testAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-aop.xml");

        Dao dao = (Dao) ac.getBean("daoImpl");
        dao.select();
//        dao.insert();
    }
}
