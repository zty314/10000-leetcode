package com.tyty.daily.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/10 9:13
 */
public class TestOptional {
    public static void main(String[] args) {
        List<Bean> list1 = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            //每个元素有名字
            list1.add(new Bean(i, "aaa" + i, i + 1));
        }
//        Optional.of(list1).map(Bean::getName);
    }
}

@Data
@AllArgsConstructor
class Bean {
    private Integer id;
    private String name;
    private Integer age;
}