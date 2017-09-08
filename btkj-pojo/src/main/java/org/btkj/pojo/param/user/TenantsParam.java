package org.btkj.pojo.param.user;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;

public class TenantsParam extends Param {

	private static final long serialVersionUID = -2940397456209218393L;

	@Min(1)
	private Integer appId;
	@Min(1)
	private Integer tid;
	private String name;
	private Client client;
	private SortCol sortCol;
	private boolean asc;
	
	public Integer getAppId() {
		return appId;
	}
	
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public SortCol getSortCol() {
		return sortCol;
	}
	
	public void setSortCol(SortCol sortCol) {
		this.sortCol = sortCol;
	}
	
	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public Integer getTid() {
		return tid;
	}
	
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, Object> params() {
		if (null == tid && null == name && null == appId)
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tid)
			params.put(BtkjConsts.FIELD.TID, tid);
		if (null != name)
			params.put(BtkjConsts.FIELD.NAME, name);
		if (null != appId)
			params.put(BtkjConsts.FIELD.APP_ID, appId);
		return params;
	}
	
	public enum SortCol {
		created,
		
		updated;
	}
}
