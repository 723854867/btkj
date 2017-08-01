package org.btkj.user.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.param.validator.EmployeeMod;

public class EmployeeEditParam extends EmployeeParam {
	
	private static final long serialVersionUID = 4120403644688276398L;
	
	@EmployeeMod
	private int mod;
	@Min(1)
	private int targetId;
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getTargetId() {
		return targetId;
	}
	
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
}
