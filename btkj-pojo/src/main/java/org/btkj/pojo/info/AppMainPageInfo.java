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
	private MainTenantTips tenant;					// 当前商户信息首页信息
	private List<TenantTips> ownTenants;			// 用户商户列表（已经排除了当前租户）
	private List<TenantTips> auditTenants;			// 用户正在审核的商户列表
	
	public AppMainPageInfo() {}
	
	public AppMainPageInfo(User user) {
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
	
	public List<TenantTips> getOwnTenants() {
		return ownTenants;
	}
	
	public void setOwnTenants(List<TenantTips> ownTenants) {
		this.ownTenants = ownTenants;
	}
	
	public List<TenantTips> getAuditTenants() {
		return auditTenants;
	}
	
	public void setAuditTenants(List<TenantTips> auditTenants) {
		this.auditTenants = auditTenants;
	}
}
