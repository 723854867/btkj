package org.btkj.master.pojo.info;

import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.entity.vehicle.JianJieInsurer;

public class JianJieInsurerInfo {

	private int id;
	private int tid;
	private int insurerId;
	private String insurerName;
	private int companyId;
	private int created;
	
	public JianJieInsurerInfo(JianJieInsurer jianJieInsurer, Insurer insurer) {
		this.id = jianJieInsurer.getId();
		this.tid = jianJieInsurer.getTid();
		this.insurerId = jianJieInsurer.getInsurerId();
		if (null != insurer)
			this.insurerName = insurer.getName();
		this.companyId = jianJieInsurer.getCompanyId();
		this.created = jianJieInsurer.getCreated();
	}

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

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}
}
