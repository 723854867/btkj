package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

public class UserTips implements Serializable {

	private static final long serialVersionUID = 1722892271710237555L;

	private int uid;
	private String name;
	private String avatar;
	private String identity;
	private String identityFace;
	private String identityBack;
	
	public UserTips() {}
	
	public UserTips(User user) {
		this.uid = user.getUid();
		this.name = user.getName();
		this.avatar = user.getAvatar();
		this.identity = user.getIdentity();
		this.identityFace = user.getIdentityFace();
		this.identityBack = user.getIdentityBack();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
