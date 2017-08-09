package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;

public class VehicleOrderParam extends EmployeeParam {

	private static final long serialVersionUID = -7802250357124586933L;

	@NotNull
	@Size(min = 1, max = 100)
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
