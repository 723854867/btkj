package org.btkj.pojo.entity.config;

import org.rapid.util.common.model.UniqueModel;

public class Area implements UniqueModel<Integer> {

	private static final long serialVersionUID = -6748716010804985723L;

	private int code;
	private int renewalPeriod;				// 续保期限
	private boolean vehiclePriceNoTax;		// 新车购置价是否不含税
	private int biHuId;
	private int created;
	private int updated;

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

	@Override
	public Integer key() {
		return code;
	}
}
