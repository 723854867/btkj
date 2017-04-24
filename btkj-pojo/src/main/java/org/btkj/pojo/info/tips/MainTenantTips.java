package org.btkj.pojo.info.tips;

import java.util.List;

import org.btkj.pojo.entity.Tenant;

/**
 * 租户首页展示信息
 * 
 * @author ahab
 */
public class MainTenantTips extends TenantTips {

	private static final long serialVersionUID = -6017793680818781117L;
	
	private int privilege;															// 权限模值，客户端自行判断每个模块是否开通
	private String region;															// 投保地区
	private String communityBackground;												// 咨询图
	private List<BannerTips> bannerList;											// banner 列表
	private List<NonAutoInsuranceTips> nonAutoInsuranceList;						// 非车险信息
	
	public MainTenantTips() {}
	
	public MainTenantTips(Tenant tenant) {
		super(tenant);
	}
	
	public int getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public List<BannerTips> getBannerList() {
		return bannerList;
	}
	
	public void setBannerList(List<BannerTips> bannerList) {
		this.bannerList = bannerList;
	}
	
	public String getCommunityBackground() {
		return communityBackground;
	}
	
	public void setCommunityBackground(String communityBackground) {
		this.communityBackground = communityBackground;
	}
	
	public List<NonAutoInsuranceTips> getNonAutoInsuranceList() {
		return nonAutoInsuranceList;
	}
	
	public void setNonAutoInsuranceList(List<NonAutoInsuranceTips> nonAutoInsuranceList) {
		this.nonAutoInsuranceList = nonAutoInsuranceList;
	}
}
