package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

public class UserTips implements Serializable {

	private static final long serialVersionUID = 1722892271710237555L;

	private int uid;
	private String name;
	private String avatar;
	
	public UserTips() {}
	
	public UserTips(User user) {
		this.uid = user.getUid();
		this.name = user.getName();
		this.avatar = user.getAvatar();
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
}