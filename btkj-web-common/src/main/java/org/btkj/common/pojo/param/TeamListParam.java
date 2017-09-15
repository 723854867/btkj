package org.btkj.common.pojo.param;

import org.btkj.pojo.param.EmployeeParam;

public class TeamListParam extends EmployeeParam {

	private static final long serialVersionUID = -7748802605991406752L;

	private int mod;
	private int endTime;
	private int beginTime;
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public int getBeginTime() {
		return beginTime;
	}
	
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
}
