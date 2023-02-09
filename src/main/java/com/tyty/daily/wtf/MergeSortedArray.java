package com.tyty.daily.wtf;

import java.util.Arrays;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/8 14:01
 */
public class MergeSortedArray {

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int i = m, l = m + n; i < l; i++) {
			nums1[i] = nums2[i - m];
		}
		Arrays.sort(nums1);
	}

	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int m = 3;
		int[] nums2 = {2, 5, 6};
		int n = 3;
		merge(nums1, m, nums2, n);
		Arrays.stream(nums1).forEach(a -> System.out.print(a + " "));
	}
}
