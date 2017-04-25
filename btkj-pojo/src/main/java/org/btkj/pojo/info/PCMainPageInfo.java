package org.btkj.pojo.info;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.mainpage.IMainPageInfo;

public class PCMainPageInfo implements IMainPageInfo {

	private static final long serialVersionUID = 6888388009819385126L;

	private int uid;
	private String name;
	private String mobile;
	
	public PCMainPageInfo() {}
	
	public PCMainPageInfo(User user) {
		this.uid = user.getUid();
		this.name = user.getName();
		this.mobile = user.getMobile();
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
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
