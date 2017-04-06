package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MainPageInfo implements IMainPageInfo {

	private static final long serialVersionUID = 5161836489569937410L;

	private int uid;								// 用户唯一标识 ID
	
	public MainPageInfo() {}
	
	public MainPageInfo(User user) {
		this.uid = user.getUid();
	}
	
	@Override
	public int getUid() {
		return uid;
	}
	
	@Override
	public void setUid(int uid) {
		this.uid = uid;
	}
}
