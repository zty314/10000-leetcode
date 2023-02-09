package com.tyty.daily.collections;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/6/14 9:00
 */
public class TestStringBuilderSize {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            builder.append(" ").append(i).append(", ");
        }
        System.out.println(builder.toString());
    }
}