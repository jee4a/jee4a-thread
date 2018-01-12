package com.jee4a.common.model;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.jee4a.common.io.cache.CacheKey;


/**
 * 缓存 key 声明
 * 
 */
public class CacheKeys {

	private static final String GLOBEL_KEY = "jee4a_redis_sharding_";

	private static CacheKey build(String key, int exprie) {
		return new CacheKey(GLOBEL_KEY + key, exprie <= 0 ? CacheKey.MINUTES30 : exprie);
	}
	 
	//定义用户缓存
	public static final CacheKey USER_MODEL = build("user_id_", CacheKey.DAY1) ;
	
	public static final CacheKey ORDER_MODEL = build("order_id_", CacheKey.DAY1) ;
	
	/**
	 * 检查cachekeys是否存在重复定义
	 */
	static {
		Field[] fields = CacheKeys.class.getDeclaredFields();
		Set<String> reSet = new HashSet<String>();
		Set<String> tmpSet = new HashSet<String>();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object object = field.get(CacheKeys.class);
				if (object instanceof CacheKey) {
					CacheKey ck = (CacheKey) object;
					for (String string : tmpSet) {
						if (string.startsWith(ck.getKey()) || ck.getKey().startsWith(string)) {
							reSet.add(ck.getKey());
							reSet.add(string);
						}
					}
					tmpSet.add(ck.getKey());
				}
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		if (!reSet.isEmpty()) {
			System.err.println("CacheKeys 出现混淆定义,请核查:" + reSet);
		}
	}

}
