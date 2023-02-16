package com.tyty.daily.wtf.thread;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/6 15:04
 */
public class InterruptCurrentThread implements Runnable {
    boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new InterruptCurrentThread(), "My Thread2");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");

    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("isInterrupted");
                return;
            }
        }
        System.out.println("My Thread exiting under request...");
    }
}

class InterruptThread implements Runnable {

    @Override
    public void run() {
        System.out.println("aaa");
        Thread.currentThread().interrupt();
        System.out.println("aaa");
    }
}