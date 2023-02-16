package com.tyty.daily.wtf;

import java.util.HashSet;
import java.util.Set;

public class GreatestLetter {

    /**
     * 给你一个由英文字母组成的字符串 s ，请你找出并返回 s 中的 最好 英文字母。
     * 返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。
     * 最好 英文字母的大写和小写形式必须 都 在 s 中出现。
     * 英文字母 b 比另一个英文字母 a 更好 的前提是：英文字母表中，b 在 a 之 后 出现。
     */
    public static void main(String[] args) {
        String ss = test("arRAzFif");
        System.out.println(ss);
    }

    public static String test(String s) {
        Set<Character> ht = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ht.add(c);
        }
        for (int i = 25; i >= 0; i--) {
            if (ht.contains((char) ('a' + i)) && ht.contains((char) ('A' + i))) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }
}
