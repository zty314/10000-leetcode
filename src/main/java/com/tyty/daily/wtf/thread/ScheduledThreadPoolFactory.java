package com.tyty.daily.wtf.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/3 10:18
 */
public class ScheduledThreadPoolFactory {

    // 心跳线程池
    private volatile static ScheduledExecutorService scheduledThreadPoolExecutor;
    private final ReentrantLock lock = new ReentrantLock();

    public ScheduledExecutorService getInstance() {
        if (scheduledThreadPoolExecutor == null) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                if (scheduledThreadPoolExecutor == null) {
                    scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
                            3,
                            new ThreadPoolTaskScheduler(),
                            new ThreadPoolExecutor.DiscardPolicy());
                }
            } finally {
                lock.unlock();
            }
        }
        return scheduledThreadPoolExecutor;
    }
}
