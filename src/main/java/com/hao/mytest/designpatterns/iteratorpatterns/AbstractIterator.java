package com.hao.mytest.designpatterns.iteratorpatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public interface AbstractIterator {

    public boolean isFirst();

    public boolean isLast();

    public void next();

    public void previous();

    public Object getNextItem();

    public Object getPreviousItem();

}
