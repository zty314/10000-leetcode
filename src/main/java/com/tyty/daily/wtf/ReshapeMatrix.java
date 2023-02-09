package com.tyty.daily.wtf;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/10 17:23
 */
public class ReshapeMatrix {
	public static int[][] matrixReshape(int[][] mat, int r, int c) {
		int m = mat.length, n = mat[0].length;
		int[][] ints = new int[r][c];
		int p = 0, q = 0;

		if(r == 0 || c == 0 || (r == m && c == n)||((m * n) != (r * c))){
			return mat;
		}

		if (m * n == r * c) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (q >= c) {
						q = 0;
						if (p < r - 1) {
							p++;
						} else {
							return ints;
						}
					}
					ints[p][q] = mat[i][j];
					q++;
				}
			}
			return ints;
		}
		return mat;
	}

	public static void main(String[] args) {
		int[][] aaa = {{1, 2}};
		int[][] ints = matrixReshapeX(aaa, 1, 1);
		for (int i = 0; i < ints.length; i++) {
			System.out.print("[");
			for (int j = 0; j < ints[0].length; j++) {
				System.out.print(ints[i][j]);
			}
			System.out.println("]");
		}
	}

	public static int[][] matrixReshapeX(int[][] mat, int r, int c) {

		//矩阵的行和列
		int i = mat.length;
		int j = mat[0].length;
		//不可操作，返回原矩阵
		if(r == 0 || c == 0 || (r == i && c == j)||((i * j) != (r * c))){
			return mat;
		}

		int[][] res = new int[r][c];

		int[] temp = new int[r * c];
		int index = 0;

		//不管目标矩阵是什么样的，先将原矩阵转换成数组，然后再转换成目标矩阵
		for(int k = 0; k < i; k++){
			for(int p = 0; p < j; p++){
				temp[index++] = mat[k][p];
			}
		}

		//数组转换成矩阵，容易找到下标的规律，而且可以减少循环的层数
		for(int k = 0; k < r; k++){
			for(int p = 0; p < c; p++){
				res[k][p] = temp[k * c + p];
			}
		}

		return res;
	}
}
