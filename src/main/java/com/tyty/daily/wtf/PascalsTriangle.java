package com.tyty.daily.wtf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/12 13:08
 */
public class PascalsTriangle {

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            ret.add(list);
            return ret;
        } else if (numRows == 2) {
            List<Integer> list1 = new ArrayList<>();
            list1.add(1);
            ret.add(list1);
            List<Integer> list2 = new ArrayList<>();
            list2.add(1);
            list2.add(1);
            ret.add(list2);
            return ret;
        } else {
            for (int i = 1; i < numRows + 1; i++) {
                List<Integer> l = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    if (j == 0 || j == i - 1) {
                        l.add(1);
                    } else {
                        l.add(ret.get(i - 2).get(j - 1) + ret.get(i - 2).get(j));
                    }

                }
                ret.add(l);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<List<Integer>> generate = generate(5);
        generate.forEach(l -> {
            l.forEach(System.out::print);
            System.out.println();
        });
    }
}
