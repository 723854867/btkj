package org.btkj.common.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.validator.custom.CarEngine;
import org.rapid.util.validator.custom.CarLicense;
import org.rapid.util.validator.custom.CarVin;

public class RenewalParam extends EmployeeParam {

	private static final long serialVersionUID = 7180004055733020530L;

	private int type;
	@CarVin
	private String vin;
	@NotNull
	private String name;
	@CarEngine
	private String engine;
	@CarLicense
	private String license;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
}
