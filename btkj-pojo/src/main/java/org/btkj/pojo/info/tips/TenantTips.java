package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.Tenant;

/**
 * 代理公司 tips 信息，只显示name、tid
 * 
 * @author ahab
 *
 */
public class TenantTips implements Serializable {

	private static final long serialVersionUID = 6752344932130668708L;

	private int tid;
	private String name;
	
	public TenantTips() {}
	
	public TenantTips(Tenant tenant) {
		this.tid = tenant.getTid();
		this.name = tenant.getName();
	}
	
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
}
