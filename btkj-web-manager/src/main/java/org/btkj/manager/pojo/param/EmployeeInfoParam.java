package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class EmployeeInfoParam extends EmployeeParam {

	private static final long serialVersionUID = 7439143708432637463L;

	@Min(1)
	private int target;
	
	public int getTarget() {
		return target;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
}
