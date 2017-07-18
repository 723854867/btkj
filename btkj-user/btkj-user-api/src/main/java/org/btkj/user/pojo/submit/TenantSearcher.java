package org.btkj.user.pojo.submit;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.vo.Page;

public class TenantSearcher extends Page {

	private static final long serialVersionUID = 1755488277849650027L;

	private Integer tid;
	private String name;
	private Integer appId;
	private Client client;
	private String sortCol;				// 排序列名
	private boolean asc;				// 是否根升序

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
	
	public String getSortCol() {
		return sortCol;
	}
	
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	
	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public Map<String, Object> params() {
		if (null == tid && null == name && null == appId)
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tid)
			params.put("tid", tid);
		if (null != name)
			params.put("name", name);
		if (null != appId)
			params.put("app_id", appId);
		return params;
	}
}
