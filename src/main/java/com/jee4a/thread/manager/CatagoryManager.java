package com.jee4a.thread.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jee4a.common.datasource.DataSource;
import com.jee4a.common.datasource.DynamicType;
import com.jee4a.common.mapper.CatagoryMapper;
import com.jee4a.common.model.Catagory;

/**
 * <p>缓存 事务管理</p> 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
@Component
public class CatagoryManager {
	
	@Resource
	private CatagoryMapper catagoryMapper ;
	
	/**
     * 保存属性不为空的记录
     */
	
    public void  insertSelective(Catagory record) {
    	catagoryMapper.insertSelective(record) ;
    }

    /**
     * 根据主键查询记录
     */
    @DataSource(DynamicType.READ)
    public Catagory selectByPrimaryKey(Integer id) {
    	return catagoryMapper.selectByPrimaryKey(id) ;
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    @DataSource(DynamicType.WRITE)
    public void  updateByPrimaryKeySelective(Catagory record) {
    	catagoryMapper.updateByPrimaryKeySelective(record) ;
    }
}
