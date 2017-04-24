package org.btkj.pojo.info;

import java.util.List;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.mainpage.IMainPageInfo;
import org.btkj.pojo.info.tips.MainTenantTips;
import org.btkj.pojo.info.tips.TenantTips;

/**
 * app 首页信息
 * 
 * @author ahab
 */
public class AppMainPageInfo implements IMainPageInfo {

	private static final long serialVersionUID = -5659542831623635191L;

	private int uid;
	private int msgTips;							// 消息条数显示 tips
	private MainTenantTips mainTenant;				// 当前商户信息首页信息
	private List<TenantTips> ownedTenants;			// 用户商户列表（已经排除了当前租户）
	private List<TenantTips> auditTenants;			// 用户正在审核的商户列表
	
	public AppMainPageInfo() {}
	
	public AppMainPageInfo(User user) {
		if (null != user)
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
	
	public MainTenantTips getMainTenant() {
		return mainTenant;
	}
	
	public void setMainTenant(MainTenantTips mainTenant) {
		this.mainTenant = mainTenant;
	}
	
	public List<TenantTips> getOwnedTenants() {
		return ownedTenants;
	}
	
	public void setOwnedTenants(List<TenantTips> ownedTenants) {
		this.ownedTenants = ownedTenants;
	}
	
	public List<TenantTips> getAuditTenants() {
		return auditTenants;
	}
	
	public void setAuditTenants(List<TenantTips> auditTenants) {
		this.auditTenants = auditTenants;
	}
}
