package com.jee4a.thread.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.jee4a.common.model.TOrder;
import com.jee4a.common.utils.SnowflakeIdWorker;
import com.jee4a.thread.manager.TOrderManager;

 
/**
 * <p>测试海量数据插入</p> 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
@Service
public class OrderService   {
	
	private static Logger logger = LoggerFactory.getLogger(OrderService.class) ;
	
	private final static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
	 
	@Resource
	private TOrderManager tOrderManager ;
	
	public  void multiThreadImport( final int ThreadNum,final int  size) {
		final CountDownLatch cdl= new CountDownLatch(ThreadNum);
		long startTime=System.currentTimeMillis();
		for(int i =0 ;i<ThreadNum ;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					importData(size,ThreadNum);
					cdl.countDown();
				}
			}).start();
		}
		try {
			cdl.await();
			long endTime=System.currentTimeMillis()-startTime;
			 System.out.println(ThreadNum+"个线程花费时间:" + endTime + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void importData(int num,int ThreadNum ) {
		TOrder record = null ;
		List<TOrder> list = new ArrayList<TOrder>();
		for(int i = 0; i<num/ThreadNum ;i++) {
			record = new TOrder() ;
			record.setAmount(new BigDecimal(98));
			record.setCreateTime(new Date());
			record.setCreator(5);
			record.setDescription("test");
			record.setOrderNo(Long.toString(idWorker.nextId()));
			record.setOrderState((byte)0);
			record.setPayTime(new Date());
			record.setPayWay("alipay");
			record.setProductId(0);
			list.add(record) ;
			
			if(list.size()%100 ==0) {
				//System.out.println("list.size():"+list.size());
				tOrderManager.insertBatch(list) ;
				list.clear();
			} 
		}
	}
	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		OrderService orderService = context.getBean(OrderService.class) ;
		//调整线程数测试 插入数据
		orderService.multiThreadImport(20,10000);
		System.out.println("CPU 数:"+Runtime.getRuntime().availableProcessors());
	}		 
}
