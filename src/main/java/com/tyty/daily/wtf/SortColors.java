package com.tyty.daily.wtf;

public class SortColors {

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，
     * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     *
     * @param nums
     */
    public void sortColors(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flg = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flg = true;
                }
            }
            if (!flg) {
                return;
            }
        }
    }

    /**
     * 双指针，将0排到前面，将2排到后面
     * @param array
     */
    public void sortColorsNew(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
}
