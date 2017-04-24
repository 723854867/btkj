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
		DEFAULT_CONFIG.setBannerNum(3);
	}

	private int maxTenantNum;
	private int bannerNum;
	
	public int getMaxTenantNum() {
		return maxTenantNum;
	}
	
	public void setMaxTenantNum(int maxTenantNum) {
		this.maxTenantNum = maxTenantNum;
	}
	
	public int getBannerNum() {
		return bannerNum;
	}
	
	public void setBannerNum(int bannerNum) {
		this.bannerNum = bannerNum;
	}
}
