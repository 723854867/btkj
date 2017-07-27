package org.btkj.pojo.po;

import org.rapid.util.common.model.UniqueModel;

public class TenantPO implements UniqueModel<Integer> {

	private static final long serialVersionUID = 9101905059642013405L;

	private int tid;
	private String name;
	private String contacts;
	private String contactsMobile;
	private int appId;
	private int region;
	private int teamDepth;
	private String jianJieId;
	private String license;
	private String licenseImage;
	private String nonAutoBind;
	private String servicePhone;
	private int bonusScaleCountMod;				// 规模佣金统计口径模值
	private int bonusScaleCountInsuranceMod;	// 规模佣金统计口径险企模值
	private int bonusScaleRewardMod;			// 规模佣金奖励口径模值
	private int bonusScaleRewardInsuranceMod;	// 规模佣金奖励口径险企模值
	private int jianJieFetchTime;
	private int expire;							// 到期日期
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
	
	public String getContacts() {
		return contacts;
	}
	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getContactsMobile() {
		return contactsMobile;
	}
	
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
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
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
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
	
	public int getBonusScaleCountInsuranceMod() {
		return bonusScaleCountInsuranceMod;
	}
	
	public void setBonusScaleCountInsuranceMod(int bonusScaleCountInsuranceMod) {
		this.bonusScaleCountInsuranceMod = bonusScaleCountInsuranceMod;
	}
	
	public int getBonusScaleRewardMod() {
		return bonusScaleRewardMod;
	}
	
	public void setBonusScaleRewardMod(int bonusScaleRewardMod) {
		this.bonusScaleRewardMod = bonusScaleRewardMod;
	}
	
	public int getBonusScaleRewardInsuranceMod() {
		return bonusScaleRewardInsuranceMod;
	}
	
	public void setBonusScaleRewardInsuranceMod(int bonusScaleRewardInsuranceMod) {
		this.bonusScaleRewardInsuranceMod = bonusScaleRewardInsuranceMod;
	}
	
	public int getJianJieFetchTime() {
		return jianJieFetchTime;
	}
	
	public void setJianJieFetchTime(int jianJieFetchTime) {
		this.jianJieFetchTime = jianJieFetchTime;
	}
	
	public int getExpire() {
		return expire;
	}
	
	public void setExpire(int expire) {
		this.expire = expire;
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
