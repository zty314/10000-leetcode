package com.tyty.daily.completablefuture;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/9 14:38
 */
public class TestSequence {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < 10000; i++) {
            list.add(i);
        }

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list4 = new ArrayList<>();

        list1.addAll(list.subList(0, 2500));
        list2.addAll(list.subList(2500, 5000));
        list3.addAll(list.subList(5000, 7500));
        list4.addAll(list.subList(7500, 10000));
        long l1 = System.currentTimeMillis();
        CompletableFuture<ArrayList<Integer>> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("F1结束了！");
            return list1;
        });
        CompletableFuture<ArrayList<Integer>> future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("F2结束了！");
            return list2;
        });
        CompletableFuture<ArrayList<Integer>> future3 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("F3结束了！");
            return list3;
        });
        CompletableFuture<ArrayList<Integer>> future4 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("F4结束了！");
            return list4;
        });


        ArrayList<Integer> list5 = new ArrayList<>();
        list5.addAll(future1.join());
        list5.addAll(future2.join());
        list5.addAll(future3.join());
        list5.addAll(future4.join());
        list5.forEach(j-> System.out.println(j));

        long l2 = System.currentTimeMillis();
        System.out.println("消耗时间："+(l2-l1));
    }
}