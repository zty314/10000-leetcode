package com.tyty.daily.threadpool;

import lombok.SneakyThrows;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/1/7 9:13
 */
public class TestIntern {

    String canSync = "stillSynchornized";

    public static void main(String[] args) {
        TestIntern testIntern = new TestIntern();
        String canNotSync1 = String.valueOf(testIntern.hashCode());
        String canNotSync2 = String.valueOf(testIntern.hashCode());
//        String canNotSync1 = String.valueOf(testIntern.hashCode()).intern();
//        String canNotSync2 = String.valueOf(testIntern.hashCode()).intern();
//        int canNotSync1 = 127;
//        int canNotSync2 = 127;
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    testIntern.printString(canNotSync1);
                }
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    testIntern.printString(canNotSync2);
                }
            }).start();
        }
    }

    void printString(Object canNotSync) throws InterruptedException {
        synchronized (canNotSync) {
            int i = 0;
            while (i < 10) {
                i++;
                System.out.println("线程名是: " + Thread.currentThread().getName() + ",不可以锁String, " + canNotSync);
                Thread.sleep(666L);
            }
        }
    }

    void printString() throws InterruptedException {
        synchronized (canSync) {
            int i = 0;
            while (i < 10) {
                i++;
                System.out.println("线程名是: " + Thread.currentThread().getName() + ",可以锁String, " + canSync);
                Thread.sleep(666L);
            }
        }
    }
}