package org.btkj.vehicle.mybatis.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 商户车险配置
 * 
 * @author ahab
 */
public class VehicleConfig implements UniqueModel<Integer> {

	private static final long serialVersionUID = 7614263458406025856L;

	private int tid;
	private int insurerId;
	private String account;
	private String pwd;
	private int renewLane;
	private int quoteLane;
	private int created;
	private int updated;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRenewLane() {
		return renewLane;
	}
	
	public void setRenewLane(int renewLane) {
		this.renewLane = renewLane;
	}
	
	public int getQuoteLane() {
		return quoteLane;
	}
	
	public void setQuoteLane(int quoteLane) {
		this.quoteLane = quoteLane;
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
	public Integer key() {
		return this.tid;
	}
}
