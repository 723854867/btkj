package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.po.TenantPO;

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
	private int bonusScaleCountMod;				// 规模佣金统计口径模值
	private int bonusScaleCountInsuranceMod;	// 规模佣金统计口径险企模值
	private int bonusScaleRewardMod;			// 规模佣金奖励口径模值
	private int bonusScaleRewardInsuranceMod;	// 规模佣金奖励口径险企模值
	private int created;						// 注册时间	
	private int updated;						// 最近修改时间
	
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
}
