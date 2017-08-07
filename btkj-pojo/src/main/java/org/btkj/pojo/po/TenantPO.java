package org.btkj.pojo.po;

import org.rapid.util.common.model.UniqueModel;

public class TenantPO implements UniqueModel<Integer> {

	private static final long serialVersionUID = 9101905059642013405L;

	private int tid;
	private String name;
	private String contacts;
	private String contactsMobile;
	private int appId;
	private int region;
	private int teamDepth;
	private String jianJieId;
	private String license;
	private String licenseImage;
	private String nonAutoBind;
	private String servicePhone;
	private int mod;
	private int jianJieFetchTime;
	private int expire;							// 到期日期
	private int scaleRewardTime;			// 最近的规模奖励统计时间，格式是：(year)(month)比如201405
	private String modularMod;
	private int created;
	private int updated;
	
	public enum Mod {
		SC_NPC(1),				// 统计口径：非营业客车
		SC_NPT(2),				// 统计口径：非营业货车
		SC_PC(4),				// 统计口径：营业客车
		SC_PT(8),				// 统计口径：营业货车
		SC_OTHER(16),			// 统计口径：其他
		SC_CM(32),				// 统计口径：商业险
		SC_CP(64),				// 统计口径：交强险
		RC_NPC(128),			// 奖励口径：非营业客车
		RC_NPT(256),			// 奖励口径：非营业货车
		RC_PC(512),				// 奖励口径：营业客车
		RC_PT(1024),			// 奖励口径：营业货车
		RC_OTHER(2048),			// 奖励口径：其他
		RC_CM(4096),			// 奖励口径：商业险
		RC_CP(8192);			// 奖励口径：交强险
		private int mark;
		private Mod(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public static final boolean check(int mod) {
			for (Mod temp : Mod.values()) {
				if ((temp.mark & mod) != temp.mark)
					return false;
			}
			return true;
		}
		/**
		 * 统计口径险种选项
		 * @return
		 */
		public static final int SCInsuranceMod() {
			return SC_CM.mark | SC_CP.mark;
		}
		/**
		 * 统计口径车型选项
		 * @return
		 */
		public static final Mod[] SCVehicles() {
			return new Mod[] {SC_PC, SC_PT, SC_NPC, SC_NPT, SC_OTHER};
		}
		/**
		 * 奖励口径险种选项
		 * @return
		 */
		public static final int RCInsuranceMod() {
			return RC_CM.mark | RC_CP.mark;
		}
		/**
		 * 奖励口径车型选项
		 * @return
		 */
		public static final Mod[] RCVehicles() {
			return new Mod[] {RC_PC, RC_PT, RC_NPC, RC_NPT, RC_OTHER};
		}
		public static final boolean isScaleRewardSupport(int mod) {
			boolean has = false;
			for (Mod cmod : RCVehicles()) {
				if ((cmod.mark & mod) == cmod.mark){
					has = true;
					break;
				}
			}
			if (!has)
				return false;
			return (RCInsuranceMod() & mod) != 0;
		}
		public static final boolean isScaleStatisticSupport(int mod) {
			boolean has = false;
			for (Mod cmod : SCVehicles()) {
				if ((cmod.mark & mod) == cmod.mark){
					has = true;
					break;
				}
			}
			if (!has)
				return false;
			return (SCInsuranceMod() & mod) != 0;
		}
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
	
	public String getContacts() {
		return contacts;
	}
	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getContactsMobile() {
		return contactsMobile;
	}
	
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}
	
	public int getTeamDepth() {
		return teamDepth;
	}
	
	public void setTeamDepth(int teamDepth) {
		this.teamDepth = teamDepth;
	}
	
	public String getJianJieId() {
		return jianJieId;
	}
	
	public void setJianJieId(String jianJieId) {
		this.jianJieId = jianJieId;
	}
	
	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}
	
	public String getNonAutoBind() {
		return nonAutoBind;
	}
	
	public void setNonAutoBind(String nonAutoBind) {
		this.nonAutoBind = nonAutoBind;
	}
	
	public String getServicePhone() {
		return servicePhone;
	}
	
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getJianJieFetchTime() {
		return jianJieFetchTime;
	}
	
	public void setJianJieFetchTime(int jianJieFetchTime) {
		this.jianJieFetchTime = jianJieFetchTime;
	}
	
	public int getExpire() {
		return expire;
	}
	
	public int getScaleRewardTime() {
		return scaleRewardTime;
	}
	
	public void setScaleRewardTime(int scaleRewardTime) {
		this.scaleRewardTime = scaleRewardTime;
	}
	
	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	public String getModularMod() {
		return modularMod;
	}
	
	public void setModularMod(String modularMod) {
		this.modularMod = modularMod;
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

	@Override
	public Integer key() {
		return tid;
	}
}
