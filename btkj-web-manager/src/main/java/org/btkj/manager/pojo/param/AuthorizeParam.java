package org.btkj.manager.pojo.param;

import java.util.Set;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.Param;

public class AuthorizeParam extends Param {

	private static final long serialVersionUID = 7610844185618808053L;

	@Min(1)
	private int tarId;
	private Set<Integer> modulars;
	
	public int getTarId() {
		return tarId;
	}
	
	public void setTarId(int tarId) {
		this.tarId = tarId;
	}
	
	public Set<Integer> getModulars() {
		return modulars;
	}
	
	public void setModulars(Set<Integer> modulars) {
		this.modulars = modulars;
	}
}
