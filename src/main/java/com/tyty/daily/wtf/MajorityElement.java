package com.tyty.daily.wtf;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/3 13:29
 */
public class MajorityElement {

	/**
	 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
	 * <p>
	 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
	 */
	/*public static int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int tmp = 0;
		for (int i = 0, l = nums.length; i < l; i++) {
			if (i == 0) {
				tmp = nums[i];
				continue;
			} else if (tmp == nums[i] && i + 1 >= l) {
				return nums[i];
			} else {
				if (i > l / 2) {
					return nums[i - 1];
				}
				tmp = nums[i];
			}
		}
		return nums[0];
	}*/

	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int a = nums.length/2;
		return nums[a];
	}

	public void main(String[] args) {
		int[] nums = {3, 2, 3};
		System.out.println(majorityElement(nums));
		ReentrantLock r = new ReentrantLock(true);

	}
}
