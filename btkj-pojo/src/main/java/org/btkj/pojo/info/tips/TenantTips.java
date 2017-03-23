package org.btkj.pojo.info.tips;

import java.io.Serializable;

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
	
	public TenantTips(int tid) {
		this.tid = tid;
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
