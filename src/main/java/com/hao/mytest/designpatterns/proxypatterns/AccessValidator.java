package com.hao.mytest.designpatterns.proxypatterns;

/**
 * Created by haojiahong on 16/5/24.
 */
public class AccessValidator {
    public boolean validate(String userId) {
        System.out.println("从数据库中验证用户" + userId + "是否合法..");
        if ("杨过".equals(userId)) {
            System.out.println(userId + "验证通过");
            return true;
        } else {
            System.out.println(userId + "验证失败");
            return false;
        }
    }
}
