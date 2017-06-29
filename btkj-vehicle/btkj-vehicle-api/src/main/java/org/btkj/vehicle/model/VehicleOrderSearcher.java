package org.btkj.vehicle.model;

import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.submit.Page;

public class VehicleOrderSearcher extends Page {

	private static final long serialVersionUID = 7429222681481518051L;

	private String batchId;
	private PolicyState state;
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public PolicyState getState() {
		return state;
	}
	
	public void setState(PolicyState state) {
		this.state = state;
	}
}
