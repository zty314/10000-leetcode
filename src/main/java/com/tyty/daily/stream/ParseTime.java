package com.tyty.daily.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/4/15 14:52
 */
public class ParseTime {
    public static void main(String[] args) {
        String t1 = "2022-04-15 00:00:00";
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(t1, DATE_TIME_FORMATTER);
        System.out.println(parse);
    }
}