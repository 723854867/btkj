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
	private String tname;
	private int regionId;
	private String region;															// 投保地区
	
	public TenantTips() {}
	
	public TenantTips(Tenant tenant) {
		if (null != tenant) {
			this.tid = tenant.getTid();
			this.tname = tenant.getName();
			this.regionId = tenant.getRegion();
		}
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
}
