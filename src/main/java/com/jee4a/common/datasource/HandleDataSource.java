package com.jee4a.common.datasource;

/**
 * <p>
 * 保存当前线程数据源的key
 * </p>
 * 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
public class HandleDataSource {
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/**
	 * 绑定当前线程数据源路由的key
	 * 
	 * @param key
	 */
	public static void putDataSource(String datasource) {
		holder.set(datasource);
	}

	/**
	 * 获取当前线程的数据源路由的key
	 * 
	 * @return
	 */
	public static String getDataSource() {
		return holder.get();
	}
}
