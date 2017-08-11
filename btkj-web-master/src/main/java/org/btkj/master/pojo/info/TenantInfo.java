package org.btkj.master.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.po.AppPO;
import org.btkj.pojo.po.Region;
import org.btkj.pojo.po.TenantPO;

public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 5721974763197422463L;

	private int tid;
	private String name;
	private String contacts;
	private String contactsMobile;
	private int appId;
	private String appName;
	private int region;
	private String regionName;
	private int teamDepth;
	private String jianJieId;
	private String license;
	private String licenseImage;
	private String nonAutoBind;
	private String servicePhone;
	private int expire;
	private int created;
	
	public TenantInfo(TenantPO tenant, AppPO app, Region region) {
		this.tid = tenant.getTid();
		this.name = tenant.getName();
		this.contacts = tenant.getContacts();
		this.contactsMobile = tenant.getContactsMobile();
		this.appId = app.getId();
		this.appName = app.getName();
		this.region = region.getId();
		this.regionName = region.getName();
		this.teamDepth = tenant.getTeamDepth();
		this.jianJieId = tenant.getJianJieId();
		this.license = tenant.getLicense();
		this.licenseImage = tenant.getLicenseImage();
		this.nonAutoBind = tenant.getNonAutoBind();
		this.servicePhone = tenant.getServicePhone();
		this.expire = tenant.getExpire();
		this.created = tenant.getCreated();
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
}