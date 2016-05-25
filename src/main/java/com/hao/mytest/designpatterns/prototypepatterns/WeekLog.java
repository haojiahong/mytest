package com.hao.mytest.designpatterns.prototypepatterns;

import lombok.Data;

/**
 * Created by haojiahong on 16/5/11.
 */
@Data
public class WeekLog implements Cloneable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    @Override
    protected WeekLog clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeekLog) obj;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
