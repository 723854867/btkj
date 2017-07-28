package org.btkj.pojo.param;

import javax.validation.constraints.Min;

public class EmployeeParam extends Param {

	private static final long serialVersionUID = -3328904956533416664L;

	@Min(1)
	private int employeeId;
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
