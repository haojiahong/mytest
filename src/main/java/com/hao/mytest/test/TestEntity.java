package com.hao.mytest.test;

import lombok.Data;

/**
 * 测试 new 一个新对象，里面的属性值有默认值么
 * Created by haojiahong on 16/6/17.
 */
@Data
public class TestEntity {
    private String name;
    private Integer age;
    private int height;
    private int weight;
}
