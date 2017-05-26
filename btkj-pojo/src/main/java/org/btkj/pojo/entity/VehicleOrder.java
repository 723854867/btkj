package org.btkj.pojo.entity;

import java.util.Map;

import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.Policy;
import org.btkj.pojo.model.insur.vehicle.Vehicle;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.lang.DateUtils;

public class VehicleOrder implements UniqueModel<String> {

	private static final long serialVersionUID = -3393446365795082514L;

	private String _id;
	private int tid;
	private int employeeId;
	private InsurUnit owner;							// 车主信息
	private Vehicle vehicle;							// 车辆信息
	private Map<Integer, Policy> policies;
	private int created;
	
	public VehicleOrder() {}
	
	public VehicleOrder(String _id, int tid, int employeeId, Map<Integer, Policy> policies, InsurUnit owner, Vehicle vehicle) {
		this._id = _id;
		this.tid = tid;
		this.owner = owner;
		this.vehicle = vehicle;
		this.policies = policies;
		this.employeeId = employeeId;
		this.created = DateUtils.currentTime();
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public InsurUnit getOwner() {
		return owner;
	}
	
	public void setOwner(InsurUnit owner) {
		this.owner = owner;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Map<Integer, Policy> getPolicies() {
		return policies;
	}
	
	public void setPolicies(Map<Integer, Policy> policies) {
		this.policies = policies;
	}
	
	public int getCreated() {
		return created;
	}
	
	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String key() {
		return this._id;
	}
}
