package org.btkj.common.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.DeliveryType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.validator.custom.Mobile;

public class DeliveryEditParam extends EmployeeParam {
	
	private static final long serialVersionUID = 2457391751815617035L;

	@NotNull
	private String orderId;
	@NotNull
	private DeliveryType type;
	@Min(1)
	private Integer customerId;
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX)
	private String name;
	@Mobile
	private String mobile;
	private String address;
	private Integer region;
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public DeliveryType getType() {
		return type;
	}
	
	public void setType(DeliveryType type) {
		this.type = type;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRegion() {
		return region;
	}
	
	public void setRegion(Integer region) {
		this.region = region;
	}
}
