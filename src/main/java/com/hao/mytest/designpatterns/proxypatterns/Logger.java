package com.hao.mytest.designpatterns.proxypatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public class Logger {
    public void log(String userId) {
        System.out.println("更新数据库,用户" + userId + "查询次数加1");

    }
}
