package com.tyty.daily.wtf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/20 16:25
 */
public class FirstUniqueCharacterString {
	/**
	 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
	 *
	 * @param s
	 * @return
	 */
	public static int firstUniqChar(String s) {
		Map<Character, Integer> map = new HashMap<>(26);
		char[] chars = s.toCharArray();
		for (char ch : chars) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		for (int i = 0; i < chars.length; i++) {
			if (map.get(chars[i]) == 1) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int loveleetcode = firstUniqChar("loveleetcode");
		System.out.println(loveleetcode);

	}
}
