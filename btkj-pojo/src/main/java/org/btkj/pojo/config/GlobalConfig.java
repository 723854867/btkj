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
		DEFAULT_CONFIG.setMaxInsureNum(1);
		DEFAULT_CONFIG.setMaxQuoteNum(3);
		DEFAULT_CONFIG.setTeamDepth(3);
		DEFAULT_CONFIG.setMaxBonusScaleConfig(4);
	}

	private int maxTenantNum;
	private int bannerNum;
	private int maxInsureNum;			// 一次请求同时投保的险企最大数
	private int maxQuoteNum;			// 一次请求同时报价的险企最大数
	private int teamDepth;				// 团队深度表示团队下限的层数：自己算一层
	private int maxBonusScaleConfig;	// 规模奖励配置项最大记录条数
	
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
	
	public int getMaxInsureNum() {
		return maxInsureNum;
	}
	
	public void setMaxInsureNum(int maxInsureNum) {
		this.maxInsureNum = maxInsureNum;
	}
	
	public int getMaxQuoteNum() {
		return maxQuoteNum;
	}
	
	public void setMaxQuoteNum(int maxQuoteNum) {
		this.maxQuoteNum = maxQuoteNum;
	}
	
	public int getTeamDepth() {
		return teamDepth;
	}
	
	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}
	
	public int getMaxBonusScaleConfig() {
		return maxBonusScaleConfig;
	}
	
	public void setMaxBonusScaleConfig(int maxBonusScaleConfig) {
		this.maxBonusScaleConfig = maxBonusScaleConfig;
	}
}
