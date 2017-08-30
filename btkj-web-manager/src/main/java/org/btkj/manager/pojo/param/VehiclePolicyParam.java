package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;

public class VehiclePolicyParam extends EmployeeParam {

	private static final long serialVersionUID = -8172392540856769206L;

	@NotNull
	private String policyId;
	
	public String getPolicyId() {
		return policyId;
	}
	
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
}
