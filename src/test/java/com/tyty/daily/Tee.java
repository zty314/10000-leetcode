package com.tyty.daily;

import java.util.HashMap;
import java.util.Map;
public class Tee {
    public static String formatInput(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[.|;|\\?]", " ");
    }
    public static Map<String, Integer> countWords(String input) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        if (input == null || input.length() == 0) {
            return result;
        }
        String[] split = input.split(" ");
        for (String value : split) {
            if (result.containsKey(value)) {
                result.put(value, result.get(value) + 1);
            } else {
                result.put(value, 1);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String value = "I am a good student.I am in Zhengzhou.Ha?";
        String format = formatInput(value);
        System.out.println(format);
        Map<String, Integer> r = countWords(format);
        System.out.println(r.toString());
    }
}