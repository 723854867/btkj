package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class VehicleModelsParam extends EmployeeParam {

	private static final long serialVersionUID = 67409864266600366L;

	@Min(1)
	private int deptId;
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
}
