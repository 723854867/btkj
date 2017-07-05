package org.btkj.vehicle.pojo.model;

import org.btkj.pojo.enums.VehicleOrderState;
import org.btkj.pojo.submit.Page;

public class VehicleOrderSearcher extends Page {

	private static final long serialVersionUID = 7429222681481518051L;

	private String batchId;
	private VehicleOrderState state;
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public VehicleOrderState getState() {
		return state;
	}
	
	public void setState(VehicleOrderState state) {
		this.state = state;
	}
}
