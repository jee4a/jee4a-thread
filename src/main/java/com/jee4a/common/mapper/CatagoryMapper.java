package com.jee4a.common.mapper;

import com.jee4a.common.model.Catagory;

public interface CatagoryMapper {
   
    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Catagory record);

    /**
     * 根据主键查询记录
     */
    Catagory selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Catagory record);
 
}