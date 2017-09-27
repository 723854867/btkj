package org.btkj.pojo.param.vehicle;

import org.btkj.pojo.enums.Lane;
import org.btkj.pojo.param.Param;

public class TenantInsurerEditParam extends Param {

	private static final long serialVersionUID = 8506208671909827926L;

	private int tid;
	private int insurerId;
	private Lane lane;
	private int jianJieId;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public Lane getLane() {
		return lane;
	}
	
	public void setLane(Lane lane) {
		this.lane = lane;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public int getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(int jianJieId) {
		this.jianJieId = jianJieId;
	}
}
