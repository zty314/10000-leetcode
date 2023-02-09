package com.tyty.daily.volatile_example;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/1/15 10:33
 */
public class UnderstandFinalVar {

//    final String habbit = "嗑瓜子";
//    static String habbit = new String("嗑瓜子");
//    String habbit = new String("嗑瓜子");

    int[] arr = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        UnderstandFinalVar var1 = new UnderstandFinalVar();
        UnderstandFinalVar var2 = new UnderstandFinalVar();
//        System.out.println(var1.habbit == var2.habbit);
//        System.out.println(var1.habbit.equals(var2.habbit));
        for (int i = 0; i < var1.arr.length; i++) {
            var1.arr[i]++;
        }
        for (int i = 0; i < var1.arr.length; i++) {
            System.out.print(var1.arr[i]);
        }

    }
}