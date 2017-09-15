package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.config.Region;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.TenantPO;

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
	
	public TenantInfo() {}
	
	public TenantInfo(AppPO app, TenantPO tenant, Region region) {
		this.tid = tenant.getTid();
		this.name = tenant.getName();
		this.appId = app.getId();
		this.appName = app.getName();
		this.region = region.getId();
		this.regionName = region.getName();
		this.license = tenant.getLicense();
		this.licenseImage = tenant.getLicenseImage();
		this.nonAutoBind = tenant.getNonAutoBind();
		this.servicePhone = tenant.getServicePhone();
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
}
