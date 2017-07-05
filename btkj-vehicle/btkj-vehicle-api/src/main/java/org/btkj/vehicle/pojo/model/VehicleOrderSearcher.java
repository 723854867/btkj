package org.btkj.vehicle.pojo.model;

import org.btkj.pojo.submit.Page;
import org.btkj.vehicle.pojo.OrderFilterState;

public class VehicleOrderSearcher extends Page {

	private static final long serialVersionUID = 7429222681481518051L;

	private String batchId;
	private OrderFilterState state;
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public OrderFilterState getState() {
		return state;
	}
	
	public void setState(OrderFilterState state) {
		this.state = state;
	}
}
