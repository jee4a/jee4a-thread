package com.jee4a.thread.service;

/**
 * <p>测试局部变量属于统一个线程，线程安全</p> 
 * @author tpeng 2018年1月11日
 * @email 398222836@qq.com
 */
public class MyThead1 {

	public static void main(String[] args) {
		for(int i = 0 ;i<10 ;i++) {
			test();
		}
	}
	
	public static void test() {
		Thread t = new Thread(new  MyThread2()) ;
		t.start();
	}
	
}

class MyThread2 implements  Runnable{

	/** 
	 * @author tpeng 2018年1月11日
	 * @email 398222836@qq.com
	 */
	@Override
	public void run() {
		int j  =  0;
		for(int i = 0; i < 100; i++) {  
           j++ ;
        }  
		System.out.println(Thread.currentThread().getName() + "   "   + j);
	} 
}
