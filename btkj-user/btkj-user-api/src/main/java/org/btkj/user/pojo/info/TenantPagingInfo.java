package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.TenantPO;

/**
 * 后台商户分页列表数据：(平台端)
 * 
 * @author ahab
 */
public class TenantPagingInfo implements Serializable {

	private static final long serialVersionUID = 8890307414021792777L;

	private int tid;							// 商户ID
	private String name;						// 商户名
	private int teamDepth;						// 商户团队层级数
	private int regionId;						// 所属行政区划ID
	private String regionName;					// 所属行政区划名字
	private String license;						// 营业执照正面
	private String licenseImage;				// 营业执照反面
	private String nonAutoBind;
	private String servicePhone;
	private String contacts;
	private String contractsMobile;
	private int bonusScaleCountMod;				// 规模佣金统计口径模值
	private int bonusScaleCountInsuranceMod;	// 规模佣金统计口径险企模值
	private int bonusScaleRewardMod;			// 规模佣金奖励口径模值
	private int bonusScaleRewardInsuranceMod;	// 规模佣金奖励口径险企模值
	private int created;						// 注册时间	
	private int updated;						// 最近修改时间
	private int expire;
	
	public TenantPagingInfo() {}
	
	public TenantPagingInfo(TenantPO tenant) {
		this.tid = tenant.getTid();
		this.name = tenant.getName();
		this.teamDepth = tenant.getTeamDepth();
		this.regionId = tenant.getRegion();
		this.license = tenant.getLicense();
		this.licenseImage = tenant.getLicenseImage();
		this.created = tenant.getCreated();
		this.updated = tenant.getUpdated();
		this.contacts = tenant.getContacts();
		this.contractsMobile = tenant.getContactsMobile();
		this.expire = tenant.getExpire();
	}

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

	public int getTeamDepth() {
		return teamDepth;
	}

	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
	
	public String getContacts() {
		return contacts;
	}
	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getContractsMobile() {
		return contractsMobile;
	}
	
	public void setContractsMobile(String contractsMobile) {
		this.contractsMobile = contractsMobile;
	}
	
	public int getExpire() {
		return expire;
	}
	
	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	public int getBonusScaleCountInsuranceMod() {
		return bonusScaleCountInsuranceMod;
	}
	
	public void setBonusScaleCountInsuranceMod(int bonusScaleCountInsuranceMod) {
		this.bonusScaleCountInsuranceMod = bonusScaleCountInsuranceMod;
	}
	
	public int getBonusScaleCountMod() {
		return bonusScaleCountMod;
	}
	
	public void setBonusScaleCountMod(int bonusScaleCountMod) {
		this.bonusScaleCountMod = bonusScaleCountMod;
	}
	
	public int getBonusScaleRewardInsuranceMod() {
		return bonusScaleRewardInsuranceMod;
	}
	
	public void setBonusScaleRewardInsuranceMod(int bonusScaleRewardInsuranceMod) {
		this.bonusScaleRewardInsuranceMod = bonusScaleRewardInsuranceMod;
	}
	
	public int getBonusScaleRewardMod() {
		return bonusScaleRewardMod;
	}
	
	public void setBonusScaleRewardMod(int bonusScaleRewardMod) {
		this.bonusScaleRewardMod = bonusScaleRewardMod;
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
}
