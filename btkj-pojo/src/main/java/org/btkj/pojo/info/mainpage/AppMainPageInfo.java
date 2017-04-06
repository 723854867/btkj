package org.btkj.pojo.info.mainpage;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.MainTenantTips;

/**
 * 独立 app 的首页信息
 * 
 * @author ahab
 */
public class AppMainPageInfo extends MainPageInfo {

	private static final long serialVersionUID = -616221631025940263L;
	
	private int msgTips;							// 消息条数显示 tips
	private MainTenantTips tenant;					// 当前商户信息首页信息
	
	public AppMainPageInfo() {}
	
	public AppMainPageInfo(User user) {
		super(user);
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
