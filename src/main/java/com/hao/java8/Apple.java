package com.hao.java8;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by haojiahong on 2017/6/20.
 */
@Getter
@Setter
public class Apple {
    private int weight;

    private String color;

    private String catalog;

    public Apple(int weight, String color, String catalog) {
        this.weight = weight;
        this.color = color;
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return "重量:" + weight + ",颜色:" + color + ",品类:" + catalog;
    }
}
