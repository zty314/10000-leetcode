package com.tyty.daily;

import java.util.concurrent.CompletableFuture;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/1 17:05
 */
public class FutureTest {

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.handle((r, e)->{
            if(e != null){
                System.out.println("compute failed!");
                return r;
            } else {
                System.out.println("received result is " + r);
                return r + 10;
            }
        });
        System.out.println("result: " + future2.join());
    }
}