package com.hao.mytest.designpatterns.factorymethodpatterns;

/**
 * Created by haojiahong on 16/5/10.
 */
public class FileLoggerFactory implements LoggerFactory {

    @Override
    public Logger createLogger() {
        Logger logger = new FileLogger();
        return logger;
    }
}
