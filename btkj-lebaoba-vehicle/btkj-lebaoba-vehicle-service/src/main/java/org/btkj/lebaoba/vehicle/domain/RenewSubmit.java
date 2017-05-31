package org.btkj.lebaoba.vehicle.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VehicleData")
public class RenewSubmit implements Serializable {

	private static final long serialVersionUID = -2314568933993373374L;

	private String username;
	private String password;
	private String licenseNo;
	private String vin;
	private String syr;
	private String insureProvince;
	private String companyId;
	private String productCode;

	public String getUsername() {
		return username;
	}

	@XmlElement(name = "Username")
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement(name = "Password")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	@XmlElement(name = "LicenseNo")
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getVin() {
		return vin;
	}

	@XmlElement(name = "Vin")
	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getSyr() {
		return syr;
	}

	@XmlElement(name = "Syr")
	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getInsureProvince() {
		return insureProvince;
	}

	@XmlElement(name = "InsureProvince")
	public void setInsureProvince(String insureProvince) {
		this.insureProvince = insureProvince;
	}

	public String getCompanyId() {
		return companyId;
	}

	@XmlElement(name = "CompanyID")
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getProductCode() {
		return productCode;
	}

	@XmlElement(name = "ProductCode")
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
