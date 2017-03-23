package org.btkj.pojo.config;

/**
 * 全局配置
 * 
 * @author ahab
 */
public class GlobalConfig {
	
	public static final GlobalConfig DEFAULT_CONFIG		= new GlobalConfig();

	private int cacheExpire;				// 用户数据缓存失效时间
	
	public int getCacheExpire() {
		return cacheExpire;
	}
	
	public void setCacheExpire(int cacheExpire) {
		this.cacheExpire = cacheExpire;
	}
}
