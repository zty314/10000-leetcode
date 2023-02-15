package com.tyty.daily.wtf;

import java.util.HashMap;
import java.util.Map;

public class TupleSameProduct {

    /**
     * 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。
     * 其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
     */
    public static void main(String[] args) {
//        int[] nums = {2, 3, 4, 6};
        int[] nums = {2, 3, 4, 6, 8, 12};
        int result = test(nums);
        System.out.println(result);
    }


    /**
     * https://leetcode.cn/problems/tuple-with-same-product/description/
     * n个数字，两两相乘的结果相同
     * 2 * 3 和 3 * 2 看做两种解，因此不用考虑去重问题
     * 比如，在{2,3,4,6}中，2*6 = 3*4，其中ab颠倒。cd颠倒,或ab与cd颠倒都看做为不同的结果
     * 所以，当出现一个满足条件的等式，可能的排列方式有， ab颠倒*cd颠倒*或ab与cd颠倒=2*2*2=8
     * 用hashmap的k保存积，v保存数量
     *
     * @param nums
     * @return
     */
    public static int test(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int result = nums[i] * nums[j];
                map.merge(result, 1, Integer::sum);
            }
        }
        int count = 0;
        for (int key : map.keySet()) {
            int val = map.get(key);
            if (val >= 2) {
                count += (val * (val - 1)) / 2;
            }
        }
        return count * 8;
    }

}
