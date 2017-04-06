package org.btkj.pojo.config;

/**
 * 全局配置
 * 
 * @author ahab
 */
public class GlobalConfig {
	
	public static GlobalConfig DEFAULT_CONFIG;
	
	static {
		DEFAULT_CONFIG = new GlobalConfig();
		DEFAULT_CONFIG.setMaxTenantNum(10);
	}

	private int maxTenantNum;
	
	public int getMaxTenantNum() {
		return maxTenantNum;
	}
	
	public void setMaxTenantNum(int maxTenantNum) {
		this.maxTenantNum = maxTenantNum;
	}
}
