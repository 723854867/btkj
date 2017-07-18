package org.btkj.vehicle.pojo.model;

import org.btkj.pojo.vo.Page;
import org.btkj.vehicle.pojo.OrderFilterState;

public class VehicleOrderSearcher extends Page {

	private static final long serialVersionUID = 7429222681481518051L;

	private String batchId;
	private Integer employeeId;
	private OrderFilterState state;
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public OrderFilterState getState() {
		return state;
	}
	
	public void setState(OrderFilterState state) {
		this.state = state;
	}
}
