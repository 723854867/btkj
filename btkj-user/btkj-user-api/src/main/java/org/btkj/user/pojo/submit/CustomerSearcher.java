package org.btkj.user.pojo.submit;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.info.Page;
import org.rapid.util.lang.StringUtil;

public class CustomerSearcher extends Page {

	private static final long serialVersionUID = -945733332802575737L;

	private int uid;
	private String name;
	private String mobile;
	private String license;
	private String sortCol;
	private boolean asc;
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public Map<String, String> params() {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.hasText(name))
			map.put("name", name);
		if (StringUtil.hasText(mobile))
			map.put("mobile", mobile);
		if (StringUtil.hasText(license))
			map.put("license", license);
		return map;
	}
}
