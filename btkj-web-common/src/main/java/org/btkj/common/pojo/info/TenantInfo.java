package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.bo.indentity.Employee;
import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.TenantPO;

public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 847805312799679291L;

	private int tid;
	private String name;
	private int appId;
	private String appName;
	private int region;
	private String regionName;
	private String license;
	private String licenseImage;
	private String nonAutoBind;
	private String servicePhone;
	private int bonusScaleCountMod;
	private int bonusScaleCountInsuranceMod;
	private int bonusScaleRewardMod;
	private int bonusScaleRewardInsuranceMod;
	
	public TenantInfo() {}
	
	public TenantInfo(Employee employee, Region region) {
		TenantPO po = employee.getTenant();
		AppPO app = employee.getApp();
		this.tid = po.getTid();
		this.name = po.getName();
		this.appId = app.getId();
		this.appName = app.getName();
		this.region = region.getId();
		this.regionName = region.getName();
		this.license = po.getLicense();
		this.licenseImage = po.getLicenseImage();
		this.nonAutoBind = po.getNonAutoBind();
		this.servicePhone = po.getServicePhone();
		this.bonusScaleCountMod = po.getBonusScaleCountMod();
		this.bonusScaleCountInsuranceMod = po.getBonusScaleCountInsuranceMod();
		this.bonusScaleRewardMod = po.getBonusScaleRewardMod();
		this.bonusScaleRewardInsuranceMod = po.getBonusScaleRewardInsuranceMod();
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

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
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
}
