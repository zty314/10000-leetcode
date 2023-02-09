package com.tyty.daily.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/3 10:18
 */
public class ScheduledThreadPoolFactory {

	/*心跳线程池*/
	private volatile static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	private final ReentrantLock lock = new ReentrantLock();

	Logger log = LoggerFactory.getLogger(this.getClass());

	public ScheduledThreadPoolExecutor getInstance() {
		if (scheduledThreadPoolExecutor == null) {
			final ReentrantLock lock = this.lock;
			lock.lock();
			try {
				if (scheduledThreadPoolExecutor == null) {
					scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
							1,
							new BasicThreadFactory.Builder().namingPattern("websocket-schedule-pool-%d").daemon(true).build(),
							new ThreadPoolExecutor.DiscardOldestPolicy());
				}
			} finally {
				lock.unlock();
			}
		}
		return scheduledThreadPoolExecutor;
	}

	/**
	 *
	 */
	void closeThreadPool() {
		if (scheduledThreadPoolExecutor != null) {
			try {
				scheduledThreadPoolExecutor.shutdown();
				if (scheduledThreadPoolExecutor.isTerminated()) {
					log.debug("成功关闭: " + scheduledThreadPoolExecutor.toString());
				} else {
					log.debug("关闭失败: " + scheduledThreadPoolExecutor.toString());
					scheduledThreadPoolExecutor.awaitTermination(1000 * 3, TimeUnit.MILLISECONDS);
					if (scheduledThreadPoolExecutor.isTerminated()) {
						log.debug("成功关闭: " + scheduledThreadPoolExecutor.toString());
					} else {
						log.debug("[" + scheduledThreadPoolExecutor.toString() + "]关闭失败，执行shutdownNow...");
						if (scheduledThreadPoolExecutor.shutdownNow().size() > 0) {
							log.debug("[" + scheduledThreadPoolExecutor.toString() + "]没有关闭成功");
						} else {
							log.debug("shutdownNow执行完毕，成功关闭[" + scheduledThreadPoolExecutor.toString() + "]");
						}
					}
				}
			} catch (InterruptedException e) {
				log.error("", e);
			}
		}
	}
}
