package com.tyty.daily.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/7 15:26
 */
public class TestMatch {
    public static void main(String[] args) {
        List<Person> list1 = new ArrayList<>();
        List<Person> list2 = new ArrayList<>();
        List<Person> list3 = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            //每个元素有名字
//            list1.add(new Person("aaa" + i, i + 30000));
//            if (i % 2 == 0) {
            //对2取模无名字
            list2.add(new Person("bbb" + i, i + 1));
//            }
        }

//        list1.forEach(l1 -> {
        long t1 = System.currentTimeMillis();
        boolean b = list2.stream().anyMatch(l2 -> 11111 == l2.getAge());

//        });

        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1));
        long t3 = System.currentTimeMillis();
        list2.stream().forEach(l2 -> {
            if (11111 == l2.getAge()) {
                return;
            }
        });
        long t4 = System.currentTimeMillis();

        System.out.println((t4 - t3));
    }

    @Data
    @AllArgsConstructor
    static
    class Person {
        private String name;
        private Integer age;
    }
}