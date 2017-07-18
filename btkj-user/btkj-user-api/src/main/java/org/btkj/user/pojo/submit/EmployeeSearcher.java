package org.btkj.user.pojo.submit;

import java.util.HashMap;
import java.util.Map;

import org.btkj.pojo.vo.Page;

public class EmployeeSearcher extends Page {

	private static final long serialVersionUID = -7144922898174024077L;

	private int appId;
	
	private Integer uid;
	private Integer tid;
	private Integer employeeId;
	private Integer payType;
	private Integer parentId;
	private String mobile;
	private String sortCol;
	private boolean asc;
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public Map<String, String> params() {
		if (null == tid && null == employeeId 
				&& null == parentId && null == payType
				&& null == uid)
			return null;
		Map<String, String> params = new HashMap<String, String>();
		if (null != tid)
			params.put("tid", String.valueOf(tid));
		if (null != employeeId)
			params.put("id", String.valueOf(employeeId));
		if (null != parentId)
			params.put("parent_id", String.valueOf(parentId));
		if (null != payType)
			params.put("pay_type", String.valueOf(payType));
		if (null != uid)
			params.put("uid", String.valueOf(uid));
		return params;
	}
}
