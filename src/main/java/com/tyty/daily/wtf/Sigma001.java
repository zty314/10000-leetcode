package com.tyty.daily.wtf;

import java.text.NumberFormat;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/2/9 15:41
 */
public class Sigma001 {
    public static void main(String[] args) {
        Double sum = 0.01;

        double pow = Math.pow(2, 30);
        double v = sum * pow;

        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);// 不用科学计数
        System.out.println(nf.format(v));
    }
}