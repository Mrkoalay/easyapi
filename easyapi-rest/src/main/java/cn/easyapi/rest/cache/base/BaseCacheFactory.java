package cn.easyapi.rest.cache.base;


/**
 * @Author yangtc
 * @Description 缓存工厂基类
 * @Date 下午2:48 2018/8/27
 * @Param
 * @return
 **/
public abstract class BaseCacheFactory implements ICache {

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, ILoader iLoader) {
		Object data = get(cacheName, key);
		if (data == null) {
			data = iLoader.load();
			put(cacheName, key, data);
		}
		return (T) data;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass) {
		Object data = get(cacheName, key);
		if (data == null) {
			try {
				ILoader dataLoader = iLoaderClass.newInstance();
				data = dataLoader.load();
				put(cacheName, key, data);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return (T) data;
	}

}
