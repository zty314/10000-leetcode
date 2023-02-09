package com.tyty.daily.threadpool;

import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/1/6 18:06
 */
public class TestHeartbeat {

    public static void main(String[] args) throws InterruptedException {
        TestHeartbeat heartbeat = new TestHeartbeat();
        ScheduledThreadPoolFactory poolFactory = new ScheduledThreadPoolFactory();
        ScheduledThreadPoolExecutor executor = poolFactory.getInstance();
        ScheduledFuture scheduledFuture = executor.scheduleAtFixedRate(new PingTask(), 100, 3000, TimeUnit.MILLISECONDS);
        int i =0;
        while (i<5){
            i++;
            Thread.sleep(1000L);
        }
        poolFactory.closeThreadPool();
    }

    /**
     * 发送Ping控制帧
     */
    static class PingTask extends TimerTask {

        @Override
        public void run() {
//                TestHeartbeat.this.sendPing();
            System.out.println("/\\_/\\_/\\_/\\_/\\_/\\_心跳_/\\_/\\_/\\_/\\_/\\_/\\");
        }
    }
}