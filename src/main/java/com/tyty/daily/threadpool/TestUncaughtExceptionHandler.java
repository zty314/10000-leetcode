package com.tyty.daily.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/5/20 9:58
 */
public class TestUncaughtExceptionHandler {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("异常了......");
                try {
                    throw new NullPointerException("我去NPE");
                } catch (NullPointerException npe) {
                    System.out.println("NullPointerException: " + npe.getMessage());
                    npe.getStackTrace();
                }
            }
        };
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("template-thread-%d")
                .daemon(false)
                .priority(Thread.NORM_PRIORITY)
//                .uncaughtExceptionHandler(new OneUncaughtExceptionHandler())
                .build();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 1000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20), factory, new ThreadPoolExecutor.AbortPolicy());

        executor.execute(runnable);
//        executor.submit(runnable);

//        Thread thread = new Thread(runnable);
//        thread.setUncaughtExceptionHandler(new OneUncaughtExceptionHandler());
//        thread.start();
    }

    static class OneUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("uncaughtException: " + e.getMessage());
            System.out.println("uncaughtException: " + Arrays.toString(e.getStackTrace()));

        }
    }
}