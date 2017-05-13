package org.btkj.pojo.info;

import java.io.Serializable;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialCommission;

/**
 * 雇员详细信息
 * 
 * @author sj
 *
 */
public class EmployeeInfo implements Serializable {
	private static final long serialVersionUID = -2999062720198009694L;
	
	private int uid;
	private int tid;
	private String name;
	private String identity;
	private String mobile;				//电话（账号）
	private int created;				//注册时间
	private int pid;					//邀请人id
	private String pname;				//邀请人
	private int id;						//邀请码(员工工号)
	private int integral;               //积分余额
	private int state;					//状态
	private int payType;				//支付方式
	private int scaleCommission;       //规模佣金
	private int manageCommission;      //管理佣金
	private int tagMod;						//角色模值
	private int noBusinessCar;		//非营业客车 1勾选
	private int noBusinessTruck;		//非营业货车 1勾选
	private int businessCar;			//营业客车     1勾选
	private int businessTruck;			//营业货车     1勾选
	private int takeEffectTime;		//生效时间
	private float commercialInsuranceRatio; //商业险比例
	private int commercialInsuranceType;	  //商业险比例：0减，1加
	private float compulsoryInsuranceRatio; //交强险比例
	private int compulsoryInsuranceType;    //交强险比例：0减，1加
	
	public EmployeeInfo(){};
	
	public EmployeeInfo(Employee employee ) {
		this.uid  = employee.getUid();
		this.tid = employee.getTid();
		this.name = employee.getName();
		this.identity = employee.getIdentity();
		this.mobile = employee.getMobile();
		this.created = employee.getCreated();
		this.pid = employee.getParentId();
		this.id = employee.getId();
		this.integral = employee.getIntegral();
		this.state=employee.getState();
		this.payType = employee.getPayType();
		this.scaleCommission = employee.getScaleCommission();
		this.manageCommission = employee.getManageCommission();
		this.tagMod = employee.getTagMod();
	}
	
	public EmployeeInfo(Employee employee , SpecialCommission specialCommission) {
		this.uid  = employee.getUid();
		this.tid = employee.getTid();
		this.name = employee.getName();
		this.identity = employee.getIdentity();
		this.mobile = employee.getMobile();
		this.created = employee.getCreated();
		this.pid = employee.getParentId();
		this.id = employee.getId();
		this.integral = employee.getIntegral();
		this.state=employee.getState();
		this.payType = employee.getPayType();
		this.scaleCommission = employee.getScaleCommission();
		this.manageCommission = employee.getManageCommission();
		this.tagMod = employee.getTagMod();
		this.noBusinessCar = specialCommission.getNoBusinessCar();
		this.noBusinessTruck = specialCommission.getNoBusinessTruck();
		this.businessCar = specialCommission.getBusinessCar();
		this.businessTruck = specialCommission.getBusinessTruck();
		this.takeEffectTime = specialCommission.getTakeEffectTime();
		this.commercialInsuranceRatio = specialCommission.getCommercialInsuranceRatio();
		this.commercialInsuranceType = specialCommission.getCommercialInsuranceType();
		this.compulsoryInsuranceRatio = specialCommission.getCompulsoryInsuranceRatio();
		this.compulsoryInsuranceType = specialCommission.getCompulsoryInsuranceType();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getScaleCommission() {
		return scaleCommission;
	}

	public void setScaleCommission(int scaleCommission) {
		this.scaleCommission = scaleCommission;
	}

	public int getManageCommission() {
		return manageCommission;
	}

	public void setManageCommission(int manageCommission) {
		this.manageCommission = manageCommission;
	}

	public int getNoBusinessCar() {
		return noBusinessCar;
	}

	public void setNoBusinessCar(int noBusinessCar) {
		this.noBusinessCar = noBusinessCar;
	}

	public int getNoBusinessTruck() {
		return noBusinessTruck;
	}

	public void setNoBusinessTruck(int noBusinessTruck) {
		this.noBusinessTruck = noBusinessTruck;
	}

	public int getBusinessCar() {
		return businessCar;
	}

	public void setBusinessCar(int businessCar) {
		this.businessCar = businessCar;
	}

	public int getBusinessTruck() {
		return businessTruck;
	}

	public void setBusinessTruck(int businessTruck) {
		this.businessTruck = businessTruck;
	}

	public int getTakeEffectTime() {
		return takeEffectTime;
	}

	public void setTakeEffectTime(int takeEffectTime) {
		this.takeEffectTime = takeEffectTime;
	}

	public float getCommercialInsuranceRatio() {
		return commercialInsuranceRatio;
	}

	public void setCommercialInsuranceRatio(float commercialInsuranceRatio) {
		this.commercialInsuranceRatio = commercialInsuranceRatio;
	}

	public int getCommercialInsuranceType() {
		return commercialInsuranceType;
	}

	public void setCommercialInsuranceType(int commercialInsuranceType) {
		this.commercialInsuranceType = commercialInsuranceType;
	}

	public float getCompulsoryInsuranceRatio() {
		return compulsoryInsuranceRatio;
	}

	public void setCompulsoryInsuranceRatio(float compulsoryInsuranceRatio) {
		this.compulsoryInsuranceRatio = compulsoryInsuranceRatio;
	}

	public int getCompulsoryInsuranceType() {
		return compulsoryInsuranceType;
	}

	public void setCompulsoryInsuranceType(int compulsoryInsuranceType) {
		this.compulsoryInsuranceType = compulsoryInsuranceType;
	}

	public int getTagMod() {
		return tagMod;
	}

	public void setTagMod(int tagMod) {
		this.tagMod = tagMod;
	}

	
}
