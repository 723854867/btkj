package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class VehicleDeptsParam extends EmployeeParam {

	private static final long serialVersionUID = 6330355373984654099L;

	@Min(1)
	private int brandId;
	
	public int getBrandId() {
		return brandId;
	}
	
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
}
