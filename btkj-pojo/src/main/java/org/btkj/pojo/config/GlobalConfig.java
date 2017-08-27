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
		DEFAULT_CONFIG.setTeamDepth(3);
		DEFAULT_CONFIG.setMaxBonusScaleConfig(4);
		DEFAULT_CONFIG.setEmployeeInviteUrl("http://www.baidu.com");
	}

	private int maxTenantNum;
	private int bannerNum;
	private int maxInsureNum;			// 一次请求同时投保的险企最大数
	private int teamDepth;				// 团队深度表示团队下限的层数：自己算一层
	private int maxBonusScaleConfig;	// 规模奖励配置项最大记录条数
	private String employeeInviteUrl;	// 邀请h5地址
	
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
	
	public String getEmployeeInviteUrl() {
		return employeeInviteUrl;
	}
	
	public void setEmployeeInviteUrl(String employeeInviteUrl) {
		this.employeeInviteUrl = employeeInviteUrl;
	}
}
