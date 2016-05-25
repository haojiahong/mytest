package com.hao.mytest.designpatterns.factorymethodpatterns;

/**
 * Created by haojiahong on 16/5/10.
 */
public class FileLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("写入文件日志");
    }
}
