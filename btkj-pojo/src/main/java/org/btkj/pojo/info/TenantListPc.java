package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 平台Tenant列表信息
 * 
 * @author sj
 */
public class TenantListPc implements Serializable {

	private static final long serialVersionUID = -1079996680418465118L;

	private int tid;
	private String name;
	private String mobile;
	private int permitNum;
	private int created;
	private int expireDate;
	private int state;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getPermitNum() {
		return permitNum;
	}
	public void setPermitNum(int permitNum) {
		this.permitNum = permitNum;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
