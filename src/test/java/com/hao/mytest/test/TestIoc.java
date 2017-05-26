package com.hao.mytest.test;

import com.hao.spring.aop.Dao;
import com.hao.spring.ioc.MultiFunctionBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by haojiahong on 17/5/22.
 */
public class TestIoc {
    @Test
    public void testIoc() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-ioc.xml");

        MultiFunctionBean bean = (MultiFunctionBean) ac.getBean("multiFunctionBean");

        Map<String, String> systemPropertiesBean = (Map<String, String>)ac.getBean("systemProperties");
        for (Map.Entry<String, String> entry : systemPropertiesBean.entrySet()) {
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }

        System.out.println("==============================华丽的分隔符==============================");
        Map<String, String> systemEnvironmentBean = (Map<String, String>)ac.getBean("systemEnvironment");
        for (Map.Entry<String, String> entry : systemEnvironmentBean.entrySet()) {
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }
}
