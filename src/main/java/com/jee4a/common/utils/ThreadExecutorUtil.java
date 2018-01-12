package com.jee4a.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadExecutorUtil {
	static class DefaultThreadFactory implements ThreadFactory {
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		DefaultThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			namePrefix = "cdb-c-" + poolNumber.getAndIncrement();
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

	private static final Logger log = LoggerFactory.getLogger(ThreadExecutorUtil.class);
	private int maxThreadNum = 20000;

	private ThreadPoolExecutor executor = new ThreadPoolExecutor(this.maxThreadNum / 2 > 0 ? this.maxThreadNum / 2 : 1, // 线程池默认线程数
			this.maxThreadNum, // 最大同时线程数
			60L, // 线程池维护线程所允许的空闲时间
			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(500), // 线程池所使用的缓冲队列
			new DefaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy()) {
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);
			if (t != null) {
				log.error("线程池内任务出错：", t);
			}
		}
	};

	private static ThreadExecutorUtil instance = new ThreadExecutorUtil();

	private ThreadExecutorUtil() {
	}

	public static void execute(Runnable runnable) {
		log.debug("Active thread:" + instance.executor.getActiveCount() + ",wait threads:"
				+ instance.executor.getQueue().size() + ",largest wait:" + instance.executor.getLargestPoolSize()
				+ ",complete:" + instance.executor.getCompletedTaskCount());
		instance.executor.execute(runnable);
	}

	public static int getWaiteQueueSize() {
		return instance.executor.getQueue().size();
	}

	public static long getComplete() {
		return instance.executor.getCompletedTaskCount();
	}

	public static int getActive() {
		return instance.executor.getActiveCount();
	}

	public static void resetThreadPool(int maxThreadNum, int bockingQuenuSize) {
		instance.executor.shutdown();
		instance.executor = new ThreadPoolExecutor(maxThreadNum / 2 > 0 ? maxThreadNum / 2 : 1, // 线程池默认线程数
				maxThreadNum, // 最大同时线程数
				60L, // 线程池维护线程所允许的空闲时间
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(bockingQuenuSize), // 线程池所使用的缓冲队列
				new DefaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy()) {
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				if (t != null) {
					log.error("线程池内任务出错：", t);
				}
			}
		};
	}
}
