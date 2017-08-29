package org.btkj.master.pojo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.btkj.pojo.entity.AppPO;
import org.btkj.pojo.entity.Region;
import org.btkj.pojo.entity.TenantPO;
import org.btkj.vehicle.pojo.entity.TenantInsurer;
import org.rapid.util.lang.CollectionUtil;

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
	private String license;
	private String licenseImage;
	private String nonAutoBind;
	private String servicePhone;
	private String jianJieId;
	private String biHuAgent;
	private String biHuKey;
	private String leBaoBaUsername;
	private String leBaoBaPassword;
	private int expire;
	private int created;
	private List<Insurer> insurers;
	
	public TenantInfo(TenantPO tenant, AppPO app, Region region, Map<String, TenantInsurer> tinsurers, Map<Integer, org.btkj.pojo.entity.Insurer> insurers) {
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
		this.biHuAgent = tenant.getBiHuAgent();
		this.biHuKey = tenant.getBiHuKey();
		this.leBaoBaUsername = tenant.getLeBaoBaUsername();
		this.leBaoBaPassword = tenant.getLeBaoBaPassword();
		if (!CollectionUtil.isEmpty(tinsurers)) {
			this.insurers = new ArrayList<Insurer>();
			for (TenantInsurer tinsurer : tinsurers.values()) 
				this.insurers.add(new Insurer(tinsurer, insurers.get(tinsurer.getInsurerId())));
		}
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
	
	public String getBiHuAgent() {
		return biHuAgent;
	}
	
	public String getBiHuKey() {
		return biHuKey;
	}
	
	public String getLeBaoBaPassword() {
		return leBaoBaPassword;
	}
	
	public String getLeBaoBaUsername() {
		return leBaoBaUsername;
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
	
	public List<Insurer> getInsurers() {
		return insurers;
	}
	
	public void setInsurers(List<Insurer> insurers) {
		this.insurers = insurers;
	}
	
	private class Insurer implements Serializable {
		private static final long serialVersionUID = -9082097044068401060L;
		private String key;
		private int insurerId;
		private String insurerName;
		private int lane;
		private int created;
		private int updated;
		public Insurer(TenantInsurer tinsurer, org.btkj.pojo.entity.Insurer insurer) {
			this.key = tinsurer.getKey();
			this.insurerId = tinsurer.getInsurerId();
			if (null != insurer)
				this.insurerName = insurer.getName();
			this.lane = tinsurer.getLane();
			this.created = tinsurer.getCreated();
			this.updated = tinsurer.getUpdated();
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getInsurerId() {
			return insurerId;
		}
		public void setInsurerId(int insurerId) {
			this.insurerId = insurerId;
		}
		public String getInsurerName() {
			return insurerName;
		}
		public void setInsurerName(String insurerName) {
			this.insurerName = insurerName;
		}
		public int getLane() {
			return lane;
		}
		public void setLane(int lane) {
			this.lane = lane;
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
}
