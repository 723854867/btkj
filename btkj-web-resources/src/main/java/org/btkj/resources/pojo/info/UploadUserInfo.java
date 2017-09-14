package org.btkj.resources.pojo.info;

import java.io.Serializable;

public class UploadUserInfo implements Serializable {

	private static final long serialVersionUID = 9090697728624039588L;

	private String avatar;
	private String identityFace;
	private String identityBack;
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
