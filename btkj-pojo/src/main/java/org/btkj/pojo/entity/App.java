package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class App implements UniqueModel<Integer> {

	private static final long serialVersionUID = 1327257995085623868L;

	private int id;
	private int region;
	private String name;
	private int mod;
	private int maxTenantsCount;
	private boolean tenantAddAutonomy;
	private String mobile;
	private String legalPerson;    //联系人
	private int permitNum;         //营业执照号
	private int expireDate;        //到时时间
	private int state;             //状态
	private int payOpen;		   //支付开通	
	private int takeScore;         //积分提现
	private int insuranceOpen;     //险种开通
	private int consultOpen;       //咨询开通
	private int mallOpen;          //共享商城开通
	private int created;
	private int updated;
	
	public enum Mod {
		
		OFF_SHELF(1);			// 是否下架
		
		private int mark;
		
		private Mod(int mark) {
			this.mark = mark;
		}
		
		public int mark() {
			return mark;
		}
		
		public boolean hit(int mod) { 
			return (this.mark & mod) == this.mark;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxTenantsCount() {
		return maxTenantsCount;
	}
	
	public void setMaxTenantsCount(int maxTenantsCount) {
		this.maxTenantsCount = maxTenantsCount;
	}
	
	public boolean isTenantAddAutonomy() {
		return tenantAddAutonomy;
	}
	
	public void setTenantAddAutonomy(boolean tenantAddAutonomy) {
		this.tenantAddAutonomy = tenantAddAutonomy;
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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public int getPermitNum() {
		return permitNum;
	}

	public void setPermitNum(int permitNum) {
		this.permitNum = permitNum;
	}

	public int getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPayOpen() {
		return payOpen;
	}

	public void setPayOpen(int payOpen) {
		this.payOpen = payOpen;
	}

	public int getTakeScore() {
		return takeScore;
	}

	public void setTakeScore(int takeScore) {
		this.takeScore = takeScore;
	}

	public int getInsuranceOpen() {
		return insuranceOpen;
	}

	public void setInsuranceOpen(int insuranceOpen) {
		this.insuranceOpen = insuranceOpen;
	}

	public int getConsultOpen() {
		return consultOpen;
	}

	public void setConsultOpen(int consultOpen) {
		this.consultOpen = consultOpen;
	}

	public int getMallOpen() {
		return mallOpen;
	}

	public void setMallOpen(int mallOpen) {
		this.mallOpen = mallOpen;
	}

	@Override
	public Integer key() {
		return id;
	}
}
