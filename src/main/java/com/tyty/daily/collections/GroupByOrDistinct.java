package com.tyty.daily.collections;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/11/30 20:09
 */
public class GroupByOrDistinct {

    public static void main(String[] args) {

//        test_groupby();
        test_partitioningBy();

    }


    public static void test_partitioningBy() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(new User
                    (i % 2 == 0 ? 0 : i % 5 == 0 ? 1 : i % 3 == 0 ? 2 : 3,
                            i % 2 == 0 ? "活跃" : "观众"));
        }
        long start = System.currentTimeMillis();
        Map<Boolean, List<User>> collect = list.stream().distinct().collect(
                Collectors.partitioningBy(uu -> uu.getName().equals("活跃")));
        long end = System.currentTimeMillis();
        System.out.println("distinct耗时：");
        System.out.println(end - start);
    }


    public static void test_groupby() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(new User
                    (i % 2 == 0 ? 0 : i % 5 == 0 ? 1 : i % 3 == 0 ? 2 : 3,
                            i % 2 == 0 ? "活跃" : "观众"));
        }
        long start = System.currentTimeMillis();
        Map<String, List<User>> collect = new HashSet<>(list).stream().collect(Collectors.groupingBy(User::getName));
//        list.stream().distinct().collect(Collectors.groupingBy(User::getName));
        long end = System.currentTimeMillis();
        System.out.println("groupby耗时：");
        System.out.println(end - start);
    }
}

@Data
@AllArgsConstructor
class User {
    private Integer id;
    private String name;
}
