package org.btkj.pojo.bo;

import java.io.Serializable;

public class Recipient implements Serializable {

	private static final long serialVersionUID = -2474240367804216679L;

	private String name;
	private String mobile;
	private String addressPrefix;
	private String addressSuffix;

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

	public String getAddressPrefix() {
		return addressPrefix;
	}

	public void setAddressPrefix(String addressPrefix) {
		this.addressPrefix = addressPrefix;
	}

	public String getAddressSuffix() {
		return addressSuffix;
	}

	public void setAddressSuffix(String addressSuffix) {
		this.addressSuffix = addressSuffix;
	}
}
