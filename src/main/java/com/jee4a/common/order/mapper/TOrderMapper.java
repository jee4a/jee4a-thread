package com.jee4a.common.order.mapper;

import java.util.List;

import com.jee4a.common.model.TOrder;

public interface TOrderMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TOrder record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TOrder record);

    int insertBatch(List<TOrder> list);
    
    
    /**
     * 根据主键查询记录
     */
    TOrder selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TOrder record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TOrder record);
}