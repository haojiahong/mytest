package com.hao.mytest.designpatterns.prototypepatterns;

/**
 * Created by haojiahong on 16/5/11.
 */
public class Client {
    public static void main(String[] args) {
        WeekLog logPrevious, logNew;
        logPrevious = new WeekLog();
        logPrevious.setName("haojiahong");
        Attachment attachment = new Attachment();
        logPrevious.setAttachment(attachment);
        //克隆新对象
        logNew = logPrevious.clone();
        System.out.println("周报是否相同：" + (logPrevious == logNew));
        System.out.println("附件是否相同：" + (logPrevious.getAttachment() == logNew.getAttachment()));
        System.out.println("周报名称是否相同：" + (logPrevious.getName() == logNew.getName()));


    }
}
