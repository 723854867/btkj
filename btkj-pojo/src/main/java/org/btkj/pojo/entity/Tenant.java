package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Tenant implements UniqueModel<Integer> {

	private static final long serialVersionUID = 9101905059642013405L;

	private int tid;
	private String name;
	private int appId;
	private int region;
	private int teamDepth;
	private String jianJieId;
	private String licenseFace;
	private String licenseBack;
	private String nonAutoBind;
	private String servicePhone;
	private int bonusScaleCountMod;				// 规模奖励统计口径模值
	private int bonusScaleRewardMod;			// 规模奖励奖励口径模值
	private int jianJieFetchTime;
	private int created;
	private int updated;
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}
	
	public int getTeamDepth() {
		return teamDepth;
	}
	
	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}
	
	public String getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(String jianJieId) {
		this.jianJieId = jianJieId;
	}
	
	public String getLicenseFace() {
		return licenseFace;
	}
	
	public void setLicenseFace(String licenseFace) {
		this.licenseFace = licenseFace;
	}
	
	public String getLicenseBack() {
		return licenseBack;
	}
	
	public void setLicenseBack(String licenseBack) {
		this.licenseBack = licenseBack;
	}
	
	public String getNonAutoBind() {
		return nonAutoBind;
	}
	
	public void setNonAutoBind(String nonAutoBind) {
		this.nonAutoBind = nonAutoBind;
	}
	
	public String getServicePhone() {
		return servicePhone;
	}
	
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
	public int getBonusScaleCountMod() {
		return bonusScaleCountMod;
	}
	
	public void setBonusScaleCountMod(int bonusScaleCountMod) {
		this.bonusScaleCountMod = bonusScaleCountMod;
	}
	
	public int getBonusScaleRewardMod() {
		return bonusScaleRewardMod;
	}
	
	public void setBonusScaleRewardMod(int bonusScaleRewardMod) {
		this.bonusScaleRewardMod = bonusScaleRewardMod;
	}
	
	public int getJianJieFetchTime() {
		return jianJieFetchTime;
	}
	
	public void setJianJieFetchTime(int jianJieFetchTime) {
		this.jianJieFetchTime = jianJieFetchTime;
	}
	
	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}
	
	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return tid;
	}
}
