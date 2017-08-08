package org.btkj.master.pojo.param;

import java.util.Set;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.Param;

public class AuthorizeAppParam extends Param {

	private static final long serialVersionUID = 7610844185618808053L;

	@Min(1)
	private int appId;
	private Set<Integer> modulars;
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public Set<Integer> getModulars() {
		return modulars;
	}
	
	public void setModulars(Set<Integer> modulars) {
		this.modulars = modulars;
	}
}
