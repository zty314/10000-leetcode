package com.tyty.daily.wtf;

import java.lang.reflect.Field;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/1/4 14:01
 */
public class SchrodingerInteger {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = new Integer[0];
        array = (Integer[]) c.get(cache);
        array[130] = array[129];
        array[131] = array[129];
        Integer a = 1;
        if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
            System.out.println("薛定谔的a");
        }
    }
}