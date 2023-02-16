package com.tyty.daily.wtf.thread;

import lombok.SneakyThrows;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/12 9:19
 */
public class CountThread {

    static boolean flag = true;

    public static void main(String[] args) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.initialize();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setAllowCoreThreadTimeOut(true);

        taskExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (flag) {
                    int t = 1;
                    Thread.sleep(60);
                    System.out.println("线程：" + Thread.currentThread().getName() + ", 第" + t + "次获取锁失败");
                    t++;
                    if (t == 50) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });
    }


}

