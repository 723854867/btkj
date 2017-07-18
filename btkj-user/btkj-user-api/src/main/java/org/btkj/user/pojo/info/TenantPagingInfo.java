package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.po.TenantPO;

/**
 * 后台商户分页列表数据：(平台端)
 * 
 * @author ahab
 */
public class TenantPagingInfo implements Serializable {

	private static final long serialVersionUID = 8890307414021792777L;

	private int tid;							// 商户ID
	private String name;						// 商户名
	private int teamDepth;						// 商户团队层级数
	private int regionId;						// 所属行政区划ID
	private String regionName;					// 所属行政区划名字
	private String licenseFace;					// 营业执照正面
	private String licenseBack;					// 营业执照反面
	private int created;						// 注册时间	
	private int updated;						// 最近修改时间
	
	public TenantPagingInfo() {}
	
	public TenantPagingInfo(TenantPO tenant) {
		this.tid = tenant.getTid();
		this.name = tenant.getName();
		this.teamDepth = tenant.getTeamDepth();
		this.regionId = tenant.getRegion();
		this.licenseFace = tenant.getLicenseFace();
		this.licenseBack = tenant.getLicenseBack();
		this.created = tenant.getCreated();
		this.updated = tenant.getUpdated();
	}

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

	public int getTeamDepth() {
		return teamDepth;
	}

	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getLicenseFace() {
		return licenseFace;
	}

	public void setLicenseFace(String licenseFace) {
		this.licenseFace = licenseFace;
	}

	public String getLicenseBack() {
		return licenseBack;
	}

	public void setLicenseBack(String licenseBack) {
		this.licenseBack = licenseBack;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}
}
