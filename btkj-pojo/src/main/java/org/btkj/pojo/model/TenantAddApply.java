package org.btkj.pojo.model;

import org.rapid.util.common.model.UniqueModel;

public class TenantAddApply implements UniqueModel<String> {

	private static final long serialVersionUID = -8987782369941897033L;

	private String id;
	private int uid;
	private int appId;
	private String name;
	private String tname;
	private String aregion;			// app 行政区划
	private String tregion;			// 租户行政区划
	private String mobile;
	private String identity;
	
	private String reason;
	private int state = State.COMMITTED.mark;
	
	public enum State {
		COMMITTED(1),		// 已提交
		CUT_OFF(2),			// 终结申请
		RE_COMMIT(3);		// 要求修改资料重新提交
		private int mark;
		private State(int mark) {
			this.mark = mark;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getAregion() {
		return aregion;
	}
	
	public void setAregion(String aregion) {
		this.aregion = aregion;
	}
	
	public String getTregion() {
		return tregion;
	}
	
	public void setTregion(String tregion) {
		this.tregion = tregion;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void setState(State state) {
		this.state = state.mark;
	}

	@Override
	public String key() {
		return id;
	}
}
