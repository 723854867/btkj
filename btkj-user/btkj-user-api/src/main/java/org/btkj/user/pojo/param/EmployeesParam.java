package org.btkj.user.pojo.param;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.validator.custom.Mobile;

public class EmployeesParam extends EmployeeParam {

	private static final long serialVersionUID = -6103267188913760332L;

	@Min(1)
	private Integer appId;
	@Min(1)
	private Integer uid;
	@Min(1)
	private Integer tid;
	@Min(1)
	private Integer parentId;
	@Min(1)
	private Integer tarId;
	@Mobile
	private String mobile;
	private SortCol sortCol;
	private boolean asc;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	public Map<String, Object> params() {
		if (null == appId && null == tid && null == tarId && null == parentId && null == uid)
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != tid)
			params.put(BtkjConsts.FIELD.TID, tid);
		if (null != tarId)
			params.put(BtkjConsts.FIELD.ID, tarId);
		if (null != parentId)
			params.put(BtkjConsts.FIELD.PARENT_ID, parentId);
		if (null != appId)
			params.put(BtkjConsts.FIELD.APP_ID, appId);
		if (null != uid)
			params.put(BtkjConsts.FIELD.UID, uid);
		return params;
	}
	
	public enum SortCol {
		created,
		updated;
	}
}
