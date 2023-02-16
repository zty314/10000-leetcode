package com.tyty.daily.volatile_example;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2021/12/28 13:58
 */
public class FeatureVolatileExample {

    private volatile Integer i = 0;
//    private Integer i = 0;

    public static Map<String, Integer> rootMap = new HashMap<>();

    public Integer getI() {
        return i;
    }

    public void increment() {
        i++;
    }

    public void setI(Integer x) {
        i = x;
    }
}