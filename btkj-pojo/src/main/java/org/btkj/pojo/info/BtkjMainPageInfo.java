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

	private List<TenantTips> tenantList;			// 用户商户列表（已经排除了当前租户）
	private List<TenantTips> tenantAuditList;		// 用户正在审核的商户列表
	
	public BtkjMainPageInfo() {}
	
	public BtkjMainPageInfo(User user) {
		super(user);
	}

	public List<TenantTips> getTenantList() {
		return tenantList;
	}
	
	public void setTenantList(List<TenantTips> tenantList) {
		this.tenantList = tenantList;
	}
	
	public List<TenantTips> getTenantAuditList() {
		return tenantAuditList;
	}
	
	public void setTenantAuditList(List<TenantTips> tenantAuditList) {
		this.tenantAuditList = tenantAuditList;
	}
}
