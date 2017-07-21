package org.btkj.config.pojo.info;

import java.io.Serializable;

import org.btkj.config.pojo.entity.Area;
import org.btkj.pojo.po.Region;

public class AreaInfo implements Serializable {

	private static final long serialVersionUID = 3231230720418337953L;

	private int code;
	private int renewalPeriod; // 续保期限
	private boolean vehiclePriceNoTax;
	private int biHuId;
	private String name;
	private int created;
	private int updated;
	
	public AreaInfo() {}
	
	public AreaInfo(Area area, Region region) {
		this.code = area.getCode();
		this.renewalPeriod = area.getRenewalPeriod();
		this.vehiclePriceNoTax = area.isVehiclePriceNoTax();
		this.biHuId = area.getBiHuId();
		this.created = area.getCreated();
		this.updated = area.getUpdated();
		if (null != region)
			this.name = region.getName();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getRenewalPeriod() {
		return renewalPeriod;
	}

	public void setRenewalPeriod(int renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}
	
	public boolean isVehiclePriceNoTax() {
		return vehiclePriceNoTax;
	}
	
	public void setVehiclePriceNoTax(boolean vehiclePriceNoTax) {
		this.vehiclePriceNoTax = vehiclePriceNoTax;
	}

	public int getBiHuId() {
		return biHuId;
	}

	public void setBiHuId(int biHuId) {
		this.biHuId = biHuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
