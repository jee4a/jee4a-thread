package com.jee4a.thread;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jee4a.common.model.Catagory;
import com.jee4a.thread.manager.CatagoryManager;

/**
 * <p></p> 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
@Component
public class ProductTest extends BaseJunitTest {

	private static Logger logger = LoggerFactory.getLogger(ProductTest.class) ;
	
	@Resource
	private CatagoryManager catagoryManager ;
	
	@Test
	public void read() {
		Catagory catagory = catagoryManager.selectByPrimaryKey(1) ;
		logger.error("catagory : {} " , catagory.toString());
	}
	
	
	@Test
	public void write() {
		Catagory catagory = new  Catagory () ;
		catagory.setName("hongfushi apple");
		catagory.setParentId(1);
		catagory.setCreateTime(new Date());
		catagoryManager.insertSelective(catagory);
		logger.error("catagory  id  : {} " , catagory.getId());
	}
	
	@Test
	public void update() {
		Catagory catagory = new  Catagory () ;
		catagory.setName("hongfushi apple  2");
		catagory.setParentId(1);
		catagory.setCreateTime(new Date());
		catagory.setId(3);
		catagoryManager.updateByPrimaryKeySelective(catagory);
		logger.error("catagory  id  : {} " , catagory.getId());
	}
}
