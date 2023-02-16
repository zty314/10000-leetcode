package com.tyty.daily.wtf.coding;

/**
 * @author 张天野
 */
public class IsMonotonic {

    /**
     * 如果数组是单调递增或单调递减的，那么它是 单调 的。
     * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。
     * 如果对于所有 i <= j，nums[i]> = nums[j]，那么数组 nums 是单调递减的。
     * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
     */
    public boolean isMonotonic(int[] nums) {
        boolean sort = true, reverse = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                reverse = false;
            }
            if (nums[i] < nums[i - 1]) {
                sort = false;
            }
        }
        return sort || reverse;
    }

}
