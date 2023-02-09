package com.tyty.daily.wtf.thread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/3 10:37
 */
public class IsThreadPoolTerminated {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolFactory().getInstance();
		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("随地吐痰");
			}
		}, 10, 100, TimeUnit.MILLISECONDS);

		Thread.sleep(2000);

		System.out.println("关！");
		scheduledExecutorService.shutdownNow();
		Thread.sleep(2000);
		scheduledExecutorService.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("捡烟头");
			}
		}, 0, TimeUnit.MILLISECONDS);

		Thread.sleep(2000);
	}
}
