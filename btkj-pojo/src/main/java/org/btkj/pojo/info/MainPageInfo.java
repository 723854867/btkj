package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.MainTenantTips;

/**
 * 独立 app 的首页信息
 * 
 * @author ahab
 */
public class MainPageInfo implements Serializable {

	private static final long serialVersionUID = -616221631025940263L;
	
	private int uid;								// 用户唯一标识ID
	private int msgTips;							// 消息条数显示 tips
	private MainTenantTips tenant;				// 当前商户信息首页信息
	
	public MainPageInfo() {}
	
	public MainPageInfo(User user) {
		this.uid = user.getUid();
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getMsgTips() {
		return msgTips;
	}
	
	public void setMsgTips(int msgTips) {
		this.msgTips = msgTips;
	}
	
	public MainTenantTips getTenant() {
		return tenant;
	}
	
	public void setTenant(MainTenantTips tenant) {
		this.tenant = tenant;
	}
}
