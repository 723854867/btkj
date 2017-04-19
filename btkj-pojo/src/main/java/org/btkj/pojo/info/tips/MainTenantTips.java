package org.btkj.pojo.info.tips;

import java.util.List;

import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.info.InsuranceNonAutoTipsInfo;

/**
 * 租户首页展示信息
 * 
 * @author ahab
 */
public class MainTenantTips extends TenantTips {

	private static final long serialVersionUID = -6017793680818781117L;
	
	private int privilege;															// 权限模值，客户端自行判断每个模块是否开通
	private String region;															// 投保地区
	private List<BannerTips> bannerList;											// banner 列表
	private List<InsuranceNonAutoTipsInfo> insuranceNonAutoInfos;					// 非车险信息
	
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
	
	public List<InsuranceNonAutoTipsInfo> getInsuranceNonAutoInfos() {
		return insuranceNonAutoInfos;
	}
	
	public void setInsuranceNonAutoInfos(List<InsuranceNonAutoTipsInfo> insuranceNonAutoInfos) {
		this.insuranceNonAutoInfos = insuranceNonAutoInfos;
	}
}
