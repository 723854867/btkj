package org.btkj.user.pojo.info;

import org.btkj.pojo.po.TenantPO;

/**
 * 后台商户分页数据：(master 保途端)
 * 
 * @author ahab
 *
 */
public class TenantPagingMasterInfo extends TenantPagingInfo {

	private static final long serialVersionUID = 4820757142323617627L;

	private int appId; 					// 所属平台ID
	private String appName; 			// 所属平台名字
	private String jianJieId; 			// 该商户的简捷ID
	
	public TenantPagingMasterInfo(TenantPO tenant) {
		super(tenant);
		this.appId = tenant.getAppId();
		this.jianJieId = tenant.getJianJieId();
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getJianJieId() {
		return jianJieId;
	}

	public void setJianJieId(String jianJieId) {
		this.jianJieId = jianJieId;
	}
}
