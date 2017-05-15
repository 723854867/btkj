package org.btkj.pojo.info;

import java.io.Serializable;
/**
 * App详细信息
 * 
 * @author sj
 *
 */
public class AppInfo implements Serializable {
	private static final long serialVersionUID = -2999062720198009694L;
	
	private int id;
	private String name;
	private String legalPerson;        //联系人
	private int maxTenantsCount;       //可开通商家数量
	private int permitNum;             //营业执照号
	private String mobile;
	private int created;			   //注册时间
	private int state;                 //状态
	private int payOpen;		       //支付开通	
	private int takeScore;             //积分提现
	private int insuranceOpen;         //险种开通
	private int consultOpen;           //咨询开通
	private int mallOpen;              //共享商城开通
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public int getMaxTenantsCount() {
		return maxTenantsCount;
	}
	public void setMaxTenantsCount(int maxTenantsCount) {
		this.maxTenantsCount = maxTenantsCount;
	}
	public int getPermitNum() {
		return permitNum;
	}
	public void setPermitNum(int permitNum) {
		this.permitNum = permitNum;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
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
	
}
