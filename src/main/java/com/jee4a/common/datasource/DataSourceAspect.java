package com.jee4a.common.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * <p>
 * </p>
 * 
 * @author tpeng 2018年1月10日
 * @email 398222836@qq.com
 */
public class DataSourceAspect {
	/**
	 * 在dao层方法之前获取datasource对象之前在切面中指定当前线程数据源路由的key
	 */
	public void before(JoinPoint point) {

		Object target = point.getTarget();
		System.out.println("target : " + target.toString());
		String method = point.getSignature().getName();
		System.out.println("target method : " + method);
		Class<?>  classz = target.getClass();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz.getMethod(method, parameterTypes);
			System.out.println("class method : " +   m.getName());
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource data = m.getAnnotation(DataSource.class);
				System.out.println("select mysql type : " + data.value());
				HandleDataSource.putDataSource(data.value());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
