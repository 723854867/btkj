package org.btkj.pojo.param.user;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.Client;
import org.btkj.pojo.param.Param;

public class UsersParam extends Param {

	private static final long serialVersionUID = -3457891381516011910L;

	private Integer uid;
	private String name;
	private String mobile;
	private Integer appId;
	private SortCol sortCol;
	private boolean asc;
	
	private Client client;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
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
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public Map<String, Object> params() {
		if (null != uid && null != appId && null != mobile)
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != uid)
			params.put(BtkjConsts.FIELD.UID, uid);
		if (null != appId)
			params.put(BtkjConsts.FIELD.APP_ID, appId);
		if (null != mobile)
			params.put(BtkjConsts.FIELD.MOBILE, mobile);
		if (null != name)
			params.put(BtkjConsts.FIELD.NAME, name);
		return params;
	}
	
	public enum SortCol {
		
		created,
		
		updated,
		
		app_login_time,
		
		pc_login_time;
	}
}
