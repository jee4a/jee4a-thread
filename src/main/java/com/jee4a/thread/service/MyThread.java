package com.jee4a.thread.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 多线程的4种实现方式
 * </p>
 * 
 * @author tpeng 2018年1月11日
 * @email 398222836@qq.com
 */

//第一种方式 继承  MyThread
public class MyThread extends Thread {

	/**
	 * 第一种实现方式
	 * 
	 * @author tpeng 2018年1月11日
	 * @email 398222836@qq.com
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  i " + i);
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//for (int j = 0; j < 10; j++) {
			//System.out.println(Thread.currentThread().getName() + "  j " + j);
			/*MyThread m1 = new MyThread();
			MyThread m2 = new MyThread();
			m1.start();
			m2.start();*/
		//}

		// 第二种实现方式 Runnable
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		}).start();

		// 第三种实现方式 实现Callable接口通过FutureTask包装器来创建Thread线程
		new Thread(new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "aaa";
			}
		})).start();

		
		//或者 设置超时时间 在指定时间内拿不到结果，则返回null  
		ExecutorService executor = Executors.newSingleThreadExecutor();
				
		FutureTask<String> futureTask =   new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "aaa";
			}
		}) ;
		
		executor.execute(futureTask);
		
		try {
			String result = futureTask.get(5000, TimeUnit.MICROSECONDS) ;
			System.out.println("result :  " +result);
		}catch (Exception e) {
			futureTask.cancel(true) ;
		}finally {
			executor.shutdown();
		}
		
		
		
		//或者
		//第四种方式
		System.out.println("----程序开始运行----");
		Date date1 = new Date();

		int taskSize = 5;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		List<Future> list = new ArrayList<Future>();

		for (int i = 0; i < taskSize; i++) {
			Callable c = new MyCallable(i + " ");
			// 执行任务并获取Future对象
			Future f = pool.submit(c);
			// System.out.println(">>>" + f.get().toString());
			list.add(f);
		}

		pool.shutdown();

		// 获取所有并发任务的运行结果
		for (Future f : list) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			System.out.println(">>>" + f.get().toString());
		}

		Date date2 = new Date();
		System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
	}
}

class MyCallable implements Callable<Object> {

	private String taskNum;

	/**
	 * 
	 */
	public MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}

	/**
	 * @author tpeng 2018年1月12日
	 * @email 398222836@qq.com
	 */
	@Override
	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
	}

}
