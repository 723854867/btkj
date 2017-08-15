package org.btkj.vehicle.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class TenantInsurer implements UniqueModel<String> {

	private static final long serialVersionUID = 4987424246445479663L;

	private String key;
	private int tid;
	private int insurerId;
	private int lane;
	private int jianJieId;
	private int created;
	private int updated;

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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
	
	public int getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(int jianJieId) {
		this.jianJieId = jianJieId;
	}
	
	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public String key() {
		return this.key;
	}
}
