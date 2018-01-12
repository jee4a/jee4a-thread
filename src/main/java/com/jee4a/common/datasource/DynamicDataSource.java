package com.jee4a.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p></p> 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/** 
	 * @author tpeng 2018年1月10日
	 * @email 398222836@qq.com
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return HandleDataSource.getDataSource();
	}

}
