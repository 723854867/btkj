package org.btkj.vehicle.cache.domain;

import org.rapid.util.common.model.UniqueModel;

public class CfgVehicle implements UniqueModel<String> {

	private static final long serialVersionUID = 7428874386521732776L;
	
	private String model;				// 厂牌型号
	private String dept;				// 车系
	private String brand;				// 品牌
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String key() {
		return this.model;
	}
}
