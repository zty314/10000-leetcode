package com.tyty.daily.volatile_example;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2021/12/28 14:01
 */
public class runVolatile {

    private final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
//        final runVolatile runVolatile = new runVolatile();
        FeatureVolatileExample example = new FeatureVolatileExample();
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                int j = 0;
                Thread.sleep(50);
                while (j < 50) {

                    example.increment();
                    j++;
//                    System.out.println(new runVolatile().counter.get());
//                    new runVolatile().counter.addAndGet(1);
//                    System.out.println("t1读取counter：" + runVolatile.counter.getAndIncrement());

                }
                System.out.println(example.getI());
//                System.out.println("t1结束了，c= " + runVolatile.counter.get());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(50);
                while (true) {
//                    int c = runVolatile.counter.get();
//                    System.out.println("t2读取counter：" + c);
//                    if (c > 0) {
//                        System.out.println("AtomicInteger 没问题");
//                        break;
//                    }
                    Integer i = example.getI();
//                    if (i > 50) {
                    System.out.println("变量i是线程可见的: " + i);
                    t1.interrupt();
                    break;
//                    }
                }
//                System.exit(0);
            }
        });
        t1.start();
        t2.start();
    }
}