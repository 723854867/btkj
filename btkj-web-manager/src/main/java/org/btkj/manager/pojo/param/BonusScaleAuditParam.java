package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;

public class BonusScaleAuditParam extends EmployeeParam {

	private static final long serialVersionUID = 1510978752658878403L;

	@NotNull
	@Size(min = 3, max = 12)
	private String key;
	private boolean aggree = true;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public boolean isAggree() {
		return aggree;
	}
	
	public void setAggree(boolean aggree) {
		this.aggree = aggree;
	}
}
