package com.tyty.daily.wtf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/8 13:37
 */
public class FindTargetNumber {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        Map<Integer, Integer> hashtable = new HashMap<>();
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (hashtable.containsKey(target - nums[right])) {
                return new int[]{hashtable.get(target - nums[right]), right};
            } else {
                hashtable.put(nums[right], right--);
            }
            if (hashtable.containsKey(target - nums[left])) {
                return new int[]{hashtable.get(target - nums[left]), left};
            } else {
                hashtable.put(nums[left], left++);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = {-3, 4, 3, 90};
        int[] ints = twoSum(arr, 0);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
