package org.btkj.vehicle.pojo.param;

import org.btkj.pojo.param.Param;

public class JianJieInsurerEditParam extends Param {

	private static final long serialVersionUID = -348767353353379646L;

	private int id;
	private int tid;
	private int insurerId;
	private int companyId;

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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
