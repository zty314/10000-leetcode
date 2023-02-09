package com.tyty.daily.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/10 15:59
 */
public class TestJoins {

    public static void main(String[] args) {

        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-1 finish");
            return "";
        });
        CompletableFuture<Void> asycn2 = async.thenAcceptAsync(a -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-2 finish");
        });
        CompletableFuture<Void> asycn3 = async.thenAcceptAsync(a -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-3 finish");
        });
        CompletableFuture<Void> asycn4 = async.thenAcceptAsync(a -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("taks-4 finish");
        });
        CompletableFuture<Void> all = CompletableFuture.allOf(async, asycn2, asycn3, asycn4);

        all.join();
        System.out.println("task finish");
    }
}