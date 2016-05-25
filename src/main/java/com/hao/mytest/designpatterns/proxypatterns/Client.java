package com.hao.mytest.designpatterns.proxypatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public class Client {
    public static void main(String[] args) {
        Searcher searcher = new ProxySearcher();
        searcher.doSearch("杨过", "玉女心经");
    }
}
