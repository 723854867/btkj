package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.entity.user.App;
import org.btkj.pojo.entity.user.User;

public class UserTips implements Serializable {

	private static final long serialVersionUID = 1722892271710237555L;

	private int uid;
	private String name;
	private String avatar;
	
	public UserTips() {}
	
	public UserTips(EmployeeTip employee) {
		this.uid = employee.getUid();
		this.name = employee.getName();
		this.avatar = employee.getAvatar();
	}
	
	public UserTips(App app, User user) {
		this.uid = user.getUid();
		this.name = user.getName();
		if (null != user.getAvatar())
			this.avatar = AliyunResourceUtil.userResource(app, user, user.getAvatar());
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
