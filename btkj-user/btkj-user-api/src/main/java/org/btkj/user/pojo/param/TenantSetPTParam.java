package org.btkj.user.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.custom.FutureTimestamp;
import org.rapid.util.validator.custom.Mobile;

public class TenantSetPTParam extends Param {

	private static final long serialVersionUID = 1753107312834412253L;

	@Min(1)
	private int tid;
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String tname;
	@FutureTimestamp
	private Integer expire;
	private String license;
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String contacts;
	private String licenseImage;
	@Mobile
	private String contactsMobile;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}

	public Integer getExpire() {
		return expire;
	}
	
	public void setExpire(Integer expire) {
		this.expire = expire;
	}
	
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
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

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}
}
