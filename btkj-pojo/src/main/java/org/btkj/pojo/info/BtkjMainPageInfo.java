package org.btkj.pojo.info;

import java.util.List;

import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.tips.TenantTips;

/**
 * 保途 app 的主页信息：比独立 app 多了一个该用户已经拥有的和正在审核的代理公司列表
 * 
 * @author ahab
 */
public class BtkjMainPageInfo extends MainPageInfo {

	private static final long serialVersionUID = -1807435574815345534L;

	private List<TenantTips> ownTenants;		// 用户商户列表（已经排除了当前租户）
	private List<TenantTips> auditTenants;		// 用户正在审核的商户列表
	
	public BtkjMainPageInfo() {}
	
	public BtkjMainPageInfo(User user) {
		super(user);
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
