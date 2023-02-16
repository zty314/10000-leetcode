package com.tyty.daily.wtf.coding;

public class FirstIndex {

    /**
     * 给你两个字符串 haystack 和 needle ，
     * 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
     * 如果 needle 不是 haystack 的一部分，则返回  -1 。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        //内置api效率杠杠的，但是像作弊
        return haystack.indexOf(needle);
    }

    //自己写了个滑窗取值
    public static int strStrNew(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int loopSize = haystack.length() - needle.length() + 1;
        int index = -1;
        for (int i = 0; i < loopSize; i++) {
            if (haystack.substring(i, needle.length() + i).equals(needle)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int i = strStrNew("hello", "ll");
        System.out.println(i);
    }
}
