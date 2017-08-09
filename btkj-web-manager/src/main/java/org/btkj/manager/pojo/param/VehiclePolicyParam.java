package org.btkj.manager.pojo.param;

import javax.validation.constraints.NotNull;

public class VehiclePolicyParam extends EmployeeInfoParam {

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
