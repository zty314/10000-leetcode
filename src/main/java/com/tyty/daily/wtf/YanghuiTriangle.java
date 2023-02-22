package com.tyty.daily.wtf;

import java.util.ArrayList;
import java.util.List;

public class YanghuiTriangle {

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * <p>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     *
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        rowIndex += 1;
        int[] arr = new int[rowIndex];
        for (int i = 0; i < rowIndex; i++) {
            int[] tmp = new int[rowIndex];
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == rowIndex) {
                    tmp[j] = 1;
                    continue;
                }
                tmp[j] = arr[j] + arr[j - 1];
            }
            arr = tmp;
        }
        List<Integer> result = new ArrayList<>(rowIndex);
        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static List<Integer> getRowList(int rowIndex) {
        List<Integer> arr = new ArrayList<>(rowIndex);
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> tmp = new ArrayList<>(rowIndex);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == rowIndex) {
                    tmp.add(1);
                    continue;
                }
                tmp.add(arr.get(j) + arr.get(j - 1));
            }
            arr = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
//        System.out.println(getRow(5));
        System.out.println(getRowAnswer(5));
    }


    public static List<Integer> getRowAnswer(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }
}
