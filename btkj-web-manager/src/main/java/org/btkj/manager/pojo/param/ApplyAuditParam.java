package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class ApplyAuditParam extends EmployeeParam {

	private static final long serialVersionUID = -895810586313340166L;

	@Min(1)
	private int uid;
	private boolean reject;
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public boolean isReject() {
		return reject;
	}
	
	public void setReject(boolean reject) {
		this.reject = reject;
	}
}
