package com.hao.mytest.designpatterns.factorymethodpatterns;

import com.hao.mytest.designpatterns.util.XMLUtil;

/**
 * 测试工厂方法模式类
 * Created by haojiahong on 16/5/10.
 */
public class Client {
    public static void main(String[] args) {
        //客户端都可以不需要知道具体的工厂和产品是什么。
        LoggerFactory loggerFactory;
        Logger logger;
        //getBean返回的是Object，需要强制类型转换。这里在config.xml中配置了具体的factory类型，所以这里直接使用LoggerFactory就可以了。
        loggerFactory = (LoggerFactory) XMLUtil.getBean();
        logger = loggerFactory.createLogger();
        logger.writeLog();

    }
}
