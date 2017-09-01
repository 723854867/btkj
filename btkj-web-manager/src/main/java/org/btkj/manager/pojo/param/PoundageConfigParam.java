package org.btkj.manager.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class PoundageConfigParam extends EmployeeParam {

	private static final long serialVersionUID = 6714084009184458609L;

	@Min(1)
	private int insurerId;
	@Min(1)
	private int nodeId;
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public int getNodeId() {
		return nodeId;
	}
	
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
}
