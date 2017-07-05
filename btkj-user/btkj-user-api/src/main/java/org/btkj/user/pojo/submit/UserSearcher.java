package org.btkj.user.pojo.submit;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.enums.Client;
import org.btkj.pojo.submit.Page;

public class UserSearcher extends Page {

	private static final long serialVersionUID = -4228137135041489556L;

	private Integer uid;
	private Integer appId;
	private String mobile;
	private Client client;
	private String sortCol;
	private boolean asc;
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public Integer getAppId() {
		return appId;
	}
	
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	public Map<String, String> params() {
		if (null != uid && null != appId && null != mobile)
			return null;
		Map<String, String> params = new HashMap<String, String>();
		if (null != uid)
			params.put("uid", String.valueOf(uid));
		if (null != appId)
			params.put("app_id", String.valueOf(appId));
		if (null != mobile)
			params.put("mobile", mobile);
		return params;
	}
}