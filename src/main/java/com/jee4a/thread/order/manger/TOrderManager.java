package com.jee4a.thread.order.manger;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jee4a.common.model.TOrder;
import com.jee4a.common.order.mapper.TOrderMapper;

@Component
public class TOrderManager {
    
	@Resource
	private TOrderMapper tOrderMapper ;

    /**
     * 保存属性不为空的记录
     */
    public int  insertSelective(TOrder record) {
    	return  tOrderMapper.insertSelective(record) ;
    }

    /**
     */
    public int  insertBatch(List<TOrder> list) {
    	return  tOrderMapper.insertBatch(list) ;
    }
    
    
    
    /**
     * 根据主键查询记录
     */
    public TOrder selectByPrimaryKey(Integer id) {
    	return tOrderMapper.selectByPrimaryKey(id) ;
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TOrder record) {
    	return tOrderMapper.updateByPrimaryKeySelective(record) ;
    }

    
}