package cn.easyapi.rest.cache.base;

import java.util.List;

/**
 * @Author yangtc
 * @Description 通用缓存接口
 * @Date 下午2:47 2018/8/27
 * @Param
 * @return
 **/
public interface ICache {
	
	void put(String cacheName, Object key, Object value);
	
	<T> T get(String cacheName, Object key);
	
	@SuppressWarnings("rawtypes")
	List getKeys(String cacheName);
	
	void remove(String cacheName, Object key);
	
	void removeAll(String cacheName);
	
	<T> T get(String cacheName, Object key, ILoader iLoader);
	
	<T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass);
	
}
