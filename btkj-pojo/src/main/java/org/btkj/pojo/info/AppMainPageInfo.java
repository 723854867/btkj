package org.btkj.pojo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.btkj.pojo.entity.Banner;
import org.btkj.pojo.entity.NonAutoInsurance;
import org.btkj.pojo.entity.Tenant;
import org.btkj.pojo.entity.User;
import org.btkj.pojo.info.mainpage.IMainPageInfo;

/**
 * app 首页信息
 * 
 * @author ahab
 */
@SuppressWarnings("unused")
public class AppMainPageInfo implements IMainPageInfo {

	private static final long serialVersionUID = -5659542831623635191L;

	private int uid;
	private int msgTips;							// 消息条数显示 tips
	private MainTenantInfo mainTenant;				// 当前商户信息首页信息
	
	public AppMainPageInfo() {}
	
	public AppMainPageInfo(User user, Tenant tenant, List<Banner> banners, List<NonAutoInsurance> insurances) {
		this.uid = user.getUid();
		mainTenant = new MainTenantInfo(tenant, banners, insurances);
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
	
	public MainTenantInfo getMainTenant() {
		return mainTenant;
	}
	
	public void setMainTenant(MainTenantInfo mainTenant) {
		this.mainTenant = mainTenant;
	}
	
	public int getRegionId() {
		return mainTenant.getRegionId();
	}
	
	public void setRegion(String region) { 
		mainTenant.setRegion(region);
	}
	
	private class MainTenantInfo implements Serializable {
		
		private static final long serialVersionUID = -3172567990383270360L;
		
		private int tid;
		private String tname;
		private int regionId;
		private String region;
		private int privilege;														// 权限模值，客户端自行判断每个模块是否开通
		private List<BannerInfo> banners;											// banner 列表
		private List<NonAutoInsuranceInfo> nonAutoInsurances;						// 非车险信息
		public MainTenantInfo() {}
		public MainTenantInfo(Tenant tenant, List<Banner> banners, List<NonAutoInsurance> insurances) {
			if (null != tenant) {
				this.tid = tenant.getTid();
				this.tname = tenant.getName();
				this.regionId = tenant.getRegion();
			}
			if (null != banners && !banners.isEmpty()) {
				this.banners = new ArrayList<BannerInfo>(banners.size());
				for (Banner banner : banners)
					this.banners.add(new BannerInfo(banner));
			}
			if (null != insurances && !insurances.isEmpty()) {
				this.nonAutoInsurances = new ArrayList<NonAutoInsuranceInfo>(insurances.size());
				for (NonAutoInsurance insurance : insurances)
					this.nonAutoInsurances.add(new NonAutoInsuranceInfo(insurance));
			}
		}
		public int getTid() {
			return tid;
		}
		public void setTid(int tid) {
			this.tid = tid;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		public int getRegionId() {
			return regionId;
		}
		public void setRegionId(int regionId) {
			this.regionId = regionId;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public int getPrivilege() {
			return privilege;
		}
		public void setPrivilege(int privilege) {
			this.privilege = privilege;
		}
		public List<BannerInfo> getBanners() {
			return banners;
		}
		public void setBanners(List<BannerInfo> banners) {
			this.banners = banners;
		}
		public List<NonAutoInsuranceInfo> getNonAutoInsurances() {
			return nonAutoInsurances;
		}
		public void setNonAutoInsurances(List<NonAutoInsuranceInfo> nonAutoInsurances) {
			this.nonAutoInsurances = nonAutoInsurances;
		}
	}
	
	private class BannerInfo implements Serializable {
		private static final long serialVersionUID = -6927850786002740964L;
		private int id;
		private String image;
		private String link;
		public BannerInfo() {}
		public BannerInfo(Banner banner) {
			this.id = banner.getId();
			this.image = banner.getImage();
			this.link = banner.getLink();
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
	}
	
	private class NonAutoInsuranceInfo implements Serializable {
		private static final long serialVersionUID = 5726389930068471813L;
		private int type;
		private String name; 
		public NonAutoInsuranceInfo() {}
		public NonAutoInsuranceInfo(NonAutoInsurance insurance) {
			this.type = insurance.getType();
			this.name = insurance.getName();
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
