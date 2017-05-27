package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Tenant implements UniqueModel<Integer> {

	private static final long serialVersionUID = 9101905059642013405L;

	private int tid;
	private String name;
	private int appId;
	private int region;
	private int mod;
	private String privilege;
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
	private int score;             //积分余额
	private int created;
	private int updated;
	
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

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public int getCreated() {
		return created;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
