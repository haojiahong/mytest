package com.hao.mytest.designpatterns.proxypatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public class RealSearcher implements Searcher {
    @Override
    public String doSearch(String userId, String keyword) {
        System.out.println("用户" + userId + "使用关键词" + keyword + "查询商务信息");

        return "返回具体内容";
    }
}
