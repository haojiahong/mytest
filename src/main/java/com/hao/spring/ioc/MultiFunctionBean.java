package com.hao.spring.ioc;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by haojiahong on 17/5/22.
 */
public class MultiFunctionBean implements InitializingBean, BeanNameAware, BeanClassLoaderAware {
    private int propertyA;

    private int propertyB;

    public int getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(int propertyA) {
        this.propertyA = propertyA;
    }

    public int getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(int propertyB) {
        this.propertyB = propertyB;
    }

    public void initMethod() {
        System.out.println("Enter MultiFunctionBean.initMethod()");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("Enter MultiFunctionBean.setBeanClassLoader(ClassLoader classLoader)");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Enter MultiFunctionBean.setBeanName(String name)");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Enter MultiFunctionBean.afterPropertiesSet()");
    }

    @Override
    public String toString() {
        return "MultiFunctionBean [propertyA=" + propertyA + ", propertyB=" + propertyB + "]";
    }
}
