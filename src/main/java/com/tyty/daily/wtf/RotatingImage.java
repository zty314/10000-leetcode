package com.tyty.daily.wtf;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class RotatingImage {

    /**
     * 自外向内一共有不超过 n/2n/2n/2 层（单个中心元素不算一层）矩形框。对于第 timestimestimes 层矩形框，其框边长 len=nums−(times∗2)len=nums-(times*2)
     * len=nums−(times∗2)，将其顺时针分为 444 份 len−1len-1len−1 的边，对四条边进行元素的循环交换即可。
     * <p>
     * 作者：肖邦鹏
     * 链接：https://leetcode.cn/problems/rotate-image/solutions/4192/zi-wai-xiang-nei-shun-shi-zhen-xun-huan-jiao-huan-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }
        int nums = matrix.length;
        int times = 0;
        while (times <= (nums >> 1)) {
            int len = nums - (times << 1);
            for (int i = 0; i < len - 1; ++i) {
                int temp = matrix[times][times + i];
                matrix[times][times + i] = matrix[times + len - i - 1][times];
                matrix[times + len - i - 1][times] = matrix[times + len - 1][times + len - i - 1];
                matrix[times + len - 1][times + len - i - 1] = matrix[times + i][times + len - 1];
                matrix[times + i][times + len - 1] = temp;
            }
            ++times;
        }
    }


}
