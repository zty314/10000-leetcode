package com.tyty.daily.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/10 15:59
 */
public class TestJoins2 {

    public static void main(String[] args) {

        CompletableFuture<String> async1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-1 finish");
            return "task---1";
        });
        CompletableFuture<Void> async2 = async1.thenAcceptAsync(a -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-2 finish");
        });
        CompletableFuture<Void> async3 = async1.thenAcceptAsync(a -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-3 finish");
        });
        CompletableFuture<Void> all = CompletableFuture.allOf(async1, async2, async3);
        all.join();
//        async.join();
        System.out.println("task finish");

        System.out.println(async1.join());
    }
}