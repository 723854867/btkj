package org.btkj.pojo.info.user;

import org.btkj.pojo.entity.user.UserPO;

public class UserPagingMasterInfo extends UserPagingInfo {

	private static final long serialVersionUID = 8123062852287120571L;
	
	private int appId;
	private String appName;
	private String identity;
	private String identityFace;
	private String identityBack;
	
	public UserPagingMasterInfo() {}

	public UserPagingMasterInfo(UserPO user) {
		super(user);
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getIdentityFace() {
		return identityFace;
	}
	
	public void setIdentityFace(String identityFace) {
		this.identityFace = identityFace;
	}
	
	public String getIdentityBack() {
		return identityBack;
	}
	
	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}
}
