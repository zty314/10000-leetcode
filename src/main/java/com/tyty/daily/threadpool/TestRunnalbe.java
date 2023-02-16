package com.tyty.daily.threadpool;

import lombok.Data;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/5/11 14:07
 */
public class TestRunnalbe {

    static final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();

    static {
        pool.initialize();
    }

    public static void main(String[] args) throws InterruptedException {
        Run run = new Run();
        pool.submit(run);
        Thread.sleep(200);
        run.setId(2);
        Thread.sleep(200);
        pool.submit(run);
    }


}

@Data
class Run implements Runnable {
    Integer id = 1;

    @Override
    public void run() {
        System.out.println(id);
    }
}