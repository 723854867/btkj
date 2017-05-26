package org.btkj.pojo.entity;

import org.btkj.pojo.model.insur.vehicle.InsurUnit;
import org.btkj.pojo.model.insur.vehicle.InsuranceSchema;
import org.btkj.pojo.model.insur.vehicle.Vehicle;
import org.rapid.util.common.model.UniqueModel;

/**
 * 续保信息
 * 
 * @author ahab
 */
public class Renewal implements UniqueModel<Void> {

	private static final long serialVersionUID = 6082011346840718571L;

	private InsurUnit owner;			// 车主信息
	private InsurUnit insurer;			// 投保人信息
	private InsurUnit insured;			// 被保人信息
	private Vehicle vehicle;			// 车辆信息
	private InsuranceSchema schema;		// 投保方案

	public InsurUnit getOwner() {
		return owner;
	}

	public void setOwner(InsurUnit owner) {
		this.owner = owner;
	}

	public InsurUnit getInsurer() {
		return insurer;
	}

	public void setInsurer(InsurUnit insurer) {
		this.insurer = insurer;
	}

	public InsurUnit getInsured() {
		return insured;
	}

	public void setInsured(InsurUnit insured) {
		this.insured = insured;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public InsuranceSchema getSchema() {
		return schema;
	}

	public void setSchema(InsuranceSchema schema) {
		this.schema = schema;
	}

	@Override
	public Void key() {
		throw new UnsupportedOperationException("Renewal has no fixed key!");
	}
}
