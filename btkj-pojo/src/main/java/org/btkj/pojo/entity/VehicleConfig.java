package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 商户车险配置
 * 
 * @author ahab
 */
public class VehicleConfig implements UniqueModel<Integer> {

	private static final long serialVersionUID = 7614263458406025856L;

	private int id;
	private int tid;
	private int insurerId;
	private String account;
	private String pwd;
	private int renewLaneId;
	private int valuationLaneId;
	private int created;
	private int updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getRenewLaneId() {
		return renewLaneId;
	}
	
	public void setRenewLaneId(int renewLaneId) {
		this.renewLaneId = renewLaneId;
	}
	
	public int getValuationLaneId() {
		return valuationLaneId;
	}
	
	public void setValuationLaneId(int valuationLaneId) {
		this.valuationLaneId = valuationLaneId;
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
		return this.id;
	}
}
