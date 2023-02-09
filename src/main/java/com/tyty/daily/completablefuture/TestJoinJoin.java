package com.tyty.daily.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/11 9:20
 */
public class TestJoinJoin {

    static volatile int sup = 1;

    public static void main(String[] args) {
//        int sup = 1;
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return sup;
        }).thenApplyAsync(re -> {
            CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("+1");
                return 1;
            });
            CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("+2");
                return 2;
            });
            CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("+3");
                return 3;
            });
            CompletableFuture<Integer> f4 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("+4");
                return 4;
            });
            CompletableFuture<Integer> f5 = CompletableFuture.supplyAsync(() -> {
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("+5");
                return 5;
            });
            CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3, f4, f5);
            all.join();
            return 10;
        });

        System.out.println(future.join());
    }
}