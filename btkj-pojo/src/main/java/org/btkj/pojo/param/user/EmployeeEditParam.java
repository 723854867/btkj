package org.btkj.pojo.param.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.param.validator.EmployeeMod;

public class EmployeeEditParam extends EmployeeParam {
	
	private static final long serialVersionUID = 4120403644688276398L;
	
	@Min(1)
	private int targetId;
	@EmployeeMod
	private Integer mod;
	@Min(-100)
	@Max(100)
	private int CMRate;
	@Min(-100)
	@Max(100)
	private int CPRate;
	
	public Integer getMod() {
		return mod;
	}
	
	public void setMod(Integer mod) {
		this.mod = mod;
	}
	
	public int getTargetId() {
		return targetId;
	}
	
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	public int getCMRate() {
		return CMRate;
	}
	
	public void setCMRate(int cMRate) {
		CMRate = cMRate;
	}
	
	public int getCPRate() {
		return CPRate;
	}
	
	public void setCPRate(int cPRate) {
		CPRate = cPRate;
	}
}
