package com.jee4a.common.datasource;

/**
 * <p>动态数据源类型</p> 
 * 对于从库建议开启read-only，以防开发时更新从库导致主从数据冲突或不一致
 * 
 * @author tpeng 2018年1月11日
 * @email 398222836@qq.com
 */
public class DynamicType {
	public final static String   READ =  "read"  ;
	public final static String   WRITE =  "write"  ;
}
