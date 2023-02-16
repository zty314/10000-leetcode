package com.tyty.daily.wtf;

import java.util.Arrays;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/3 9:10
 */
public class SingleNumber {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        Arrays.sort(nums);
        for (int i = 0, l = nums.length; i < l; i = i + 2) {
            if (i + 1 >= l) {
                return nums[i];
            }
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            return nums[i];
        }
        return 0;
    }


    /**
     * 异或运算：
     * 对两个二进制数字取异或时，遇到相同位，得0，遇到不同位，得1
     * 例如：
     * 11010和10011的异或，结果是01001
     * 同时满足交换律和结合律
     * 比如：
     * a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
     *
     * @param nums
     * @return
     */
    public int singleNumberNew(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] ^ nums[i];
        }
        return nums[0];
    }
}
