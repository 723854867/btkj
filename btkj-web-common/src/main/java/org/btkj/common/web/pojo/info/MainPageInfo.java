package org.btkj.common.web.pojo.info;

import java.io.Serializable;
import java.util.List;

import org.btkj.pojo.entity.User;

/**
 * 首页信息
 * 
 * @author ahab
 */
public class MainPageInfo implements Serializable {

	private static final long serialVersionUID = -616221631025940263L;
	
	private int uid;								// 用户唯一标识ID
	private int msgTips;							// 消息条数显示 tips
	private TenantDetailInfo tenant;				// 当前商户信息
	private List<TenantListInfo> tenantList;		// 用户商户列表（已经排除了当前租户）
	
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
	
	public TenantDetailInfo getTenant() {
		return tenant;
	}
	
	public void setTenant(TenantDetailInfo tenant) {
		this.tenant = tenant;
	}
	
	public List<TenantListInfo> getTenantList() {
		return tenantList;
	}
	
	public void setTenantList(List<TenantListInfo> tenantList) {
		this.tenantList = tenantList;
	}
	
	/**
	 * 租户列表信息
	 * 
	 * @author ahab
	 */
	private class TenantListInfo implements Serializable {
		private static final long serialVersionUID = 6921130963365301456L;
		private int tid;
		private String name;
		public int getTid() {
			return tid;
		}
		public void setTid(int tid) {
			this.tid = tid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 租户详细信息
	 * 
	 * @author ahab
	 */
	private class TenantDetailInfo extends TenantListInfo {
		private static final long serialVersionUID = 1433510680773915007L;
		private String region;																// 投保地区
		private List<NonAutoInsuranceListInfo> nonAutoInsuranceListInfos;					// 非车险信息
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public List<NonAutoInsuranceListInfo> getNonAutoInsuranceListInfos() {
			return nonAutoInsuranceListInfos;
		}
		public void setNonAutoInsuranceListInfos(List<NonAutoInsuranceListInfo> nonAutoInsuranceListInfos) {
			this.nonAutoInsuranceListInfos = nonAutoInsuranceListInfos;
		}
	}
	
	private class NonAutoInsuranceListInfo implements Serializable {
		private static final long serialVersionUID = -753237139768160915L;
		private String name;				// 险种名
		private String icon;				// 险种图标地址
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
	}
}
