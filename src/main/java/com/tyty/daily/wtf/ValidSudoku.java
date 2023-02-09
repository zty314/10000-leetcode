package com.tyty.daily.wtf;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/12 14:49
 */
public class ValidSudoku {
	/**
	 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
	 * <p>
	 * 数字 1-9 在每一行只能出现一次。
	 * 数字 1-9 在每一列只能出现一次。
	 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
	 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
	 * <p>
	 * 注意：
	 * <p>
	 * 一个有效的数独（部分已被填充）不一定是可解的。
	 * 只需要根据以上规则，验证已经填入的数字是否有效即可
	 * <p>
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/valid-sudoku
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku(char[][] board) {
		boolean isValid = true;
		//数字 1-9 在每一行只能出现一次。
		for (int i = 0; i < 9; i++) {
			Set<Character> line = new HashSet<>();
			for (int j = 0, len = 9; j < len; j++) {
				if (board[i][j] != '.' && !line.add(board[i][j])) {
					System.out.println(11111);
					return false;
				}
			}
		}
		//数字 1-9 在每一列只能出现一次。
		for (int i = 0; i < 9; i++) {
			Set<Character> column = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char c = board[j][i];
				if (c != '.' && !column.add(c)) {
					System.out.println(222);
					return false;
				}
			}
		}
		//数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
		for (int i = 0; i < 9; i = i + 3) {

			for (int j = 0; j < 9; j = j + 3) {
				Set<Character> square = new HashSet<>();
				boolean s1 = board[i][j] != '.' && !square.add(board[i][j]);
				boolean s2 = board[i + 1][j] != '.' && !square.add(board[i + 1][j]);
				boolean s3 = board[i + 2][j] != '.' && !square.add(board[i + 2][j]);

				boolean s4 = board[i][j + 1] != '.' && !square.add(board[i][j + 1]);
				boolean s5 = board[i + 1][j + 1] != '.' && !square.add(board[i + 1][j + 1]);
				boolean s6 = board[i + 2][j + 1] != '.' && !square.add(board[i + 2][j + 1]);

				boolean s7 = board[i][j + 2] != '.' && !square.add(board[i][j + 2]);
				boolean s8 = board[i + 1][j + 2] != '.' && !square.add(board[i + 1][j + 2]);
				boolean s9 = board[i + 2][j + 2] != '.' && !square.add(board[i + 2][j + 2]);

				if (s1 || s2 || s3 || s4 || s5 || s6 || s7 || s8 || s9) {
					System.out.println(3333);
					return false;
				}
			}
		}
		return isValid;
	}


	public static void main(String[] args) {
		char[][] c = {
				{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
				{'.', '4', '.', '3', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '3', '.', '.', '1'},
				{'8', '.', '.', '.', '.', '.', '.', '2', '.'},
				{'.', '.', '2', '.', '7', '.', '.', '.', '.'},
				{'.', '1', '5', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '2', '.', '.', '.'},
				{'.', '2', '.', '9', '.', '.', '.', '.', '.'},
				{'.', '.', '4', '.', '.', '.', '.', '.', '.'}
		};

		boolean b = isValidSudoku(c);
		System.out.println(b);
	}
}
