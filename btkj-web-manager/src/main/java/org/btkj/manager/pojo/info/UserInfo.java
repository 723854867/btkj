package org.btkj.manager.pojo.info;

import org.btkj.pojo.entity.user.User;

public class UserInfo {

	private int mod;
	private String name;
	private String avatar;
	
	public UserInfo(User user) {
		this.mod = user.getMod();
		this.name = user.getName();
		this.avatar = user.getAvatar();
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
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
