package com.tyty.daily.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/23 11:10
 */
public class TestListStream {

    public static void main(String[] args) {
        int[] arr = {7, 1, 2, 3, 1, 2, 3, 4, 5, 2, 1, 2, 3, 4, 5, 6, 1, 0};
//        List result = Stream.of(arr).collect(ArrayList::new, ArrayList::add, ArrayList::addAll).stream().distinct()
//        .collect(Collectors.toList());
        List<Integer> result = Arrays.stream(arr).boxed().distinct().sorted().collect(Collectors.toList());
//        boolean allSuccess = !CollectionUtils.isEmpty(result) && result.get(0) == 1;
//        System.out.println(result.size());
        result.forEach(System.out::println);

    }
}