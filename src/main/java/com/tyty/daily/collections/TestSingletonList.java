package com.tyty.daily.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/16 10:14
 */
public class TestSingletonList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Collections.singletonList("aaa"));
        list.add("bbb");
        System.out.println(list);
        List<String> ccc = Stream.of("ccc").collect(Collectors.toList());
        ccc.add("ddd");
        System.out.println(ccc);
    }
}