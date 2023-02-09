package com.tyty.daily.volatile_example;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/1/13 19:57
 */
public class UnderstandStaticMap {

    public static void main(String[] args) {
        FeatureVolatileExample example = new FeatureVolatileExample();
        example.rootMap.put("1", 1);
        FeatureVolatileExample example2 = new FeatureVolatileExample();
        Integer integer = example2.rootMap.get("1");
        System.out.println(integer);
    }
}