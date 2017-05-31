package org.btkj.pojo.model.insur.vehicle;

import java.io.Serializable;

import org.btkj.pojo.enums.PolicyState;
import org.btkj.pojo.enums.VehicleOrderType;

/**
 * 报价信息
 * 
 * @author ahab
 */
public class Policy implements Serializable {

	private static final long serialVersionUID = -6961500653346176138L;

	private int insurerId;				// 险企ID
	private int lane;					// 线路：壁虎、乐宝吧、保途
	private VehicleOrderType type;		// 下单类型：报价、投保、报价并投保
	private PolicyState state;			// 状态
	private String desc;				// 描述
	private InsuranceSchema schema;		// 投保方案
	private PolicyDetail detail;
	
	public Policy() {}
	
	public Policy(int insurerId, int lane, VehicleOrderType type) {
		this.insurerId = insurerId;
		this.lane = lane;
		this.type = type;
		this.state = PolicyState.NEW;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public int getLane() {
		return lane;
	}
	
	public void setLane(int lane) {
		this.lane = lane;
	}
	
	public VehicleOrderType getType() {
		return type;
	}
	
	public void setType(VehicleOrderType type) {
		this.type = type;
	}
	
	public PolicyState getState() {
		return state;
	}
	
	public void setState(PolicyState state) {
		this.state = state;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public InsuranceSchema getSchema() {
		return schema;
	}
	
	public void setSchema(InsuranceSchema schema) {
		this.schema = schema;
	}
	
	public PolicyDetail getDetail() {
		return detail;
	}
	
	public void setDetail(PolicyDetail detail) {
		this.detail = detail;
	}
}
