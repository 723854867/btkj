package org.btkj.pojo.info;

import java.io.Serializable;
import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.SpecialBonus;

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
	private int score;               //积分余额
	private int state;					//状态
	private int payType;				//支付方式
	private int scaleBonus;       //规模佣金
	private int manageBonus;      //管理佣金
	private int tagMod;						//角色模值
	private int noBusinessCar;		//非营业客车 1勾选
	private int noBusinessTruck;		//非营业货车 1勾选
	private int businessCar;			//营业客车     1勾选
	private int businessTruck;			//营业货车     1勾选
	private int startTime;		//生效时间
	private float vciRatio; //商业险比例
	private int vciType;	  //商业险比例：0减，1加
	private float tciRatio; //交强险比例
	private int tciType;    //交强险比例：0减，1加
	
	public EmployeeInfo(){};
	
	public EmployeeInfo(Employee employee ) {
		this.uid  = employee.getUid();
		this.tid = employee.getTid();
		this.created = employee.getCreated();
		this.pid = employee.getParentId();
		this.id = employee.getId();
		this.score = employee.getScore();
		this.state=employee.getState();
		this.payType = employee.getPayType();
		this.scaleBonus = employee.getScaleBonus();
		this.manageBonus = employee.getManageBonus();
		this.tagMod = employee.getTagMod();
	}
	
	public EmployeeInfo(Employee employee , SpecialBonus specialBonus) {
		this.uid  = employee.getUid();
		this.tid = employee.getTid();
		this.created = employee.getCreated();
		this.pid = employee.getParentId();
		this.id = employee.getId();
		this.score = employee.getScore();
		this.state=employee.getState();
		this.payType = employee.getPayType();
		this.scaleBonus = employee.getScaleBonus();
		this.manageBonus = employee.getManageBonus();
		this.tagMod = employee.getTagMod();
		this.noBusinessCar = specialBonus.getNoBusinessCar();
		this.noBusinessTruck = specialBonus.getNoBusinessTruck();
		this.businessCar = specialBonus.getBusinessCar();
		this.businessTruck = specialBonus.getBusinessTruck();
		this.startTime = specialBonus.getStartTime();
		this.vciRatio = specialBonus.getVciRatio();
		this.vciType = specialBonus.getVciType();
		this.tciRatio = specialBonus.getTciRatio();
		this.tciType = specialBonus.getTciType();
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScaleBonus() {
		return scaleBonus;
	}

	public void setScaleBonus(int scaleBonus) {
		this.scaleBonus = scaleBonus;
	}

	public int getManageBonus() {
		return manageBonus;
	}

	public void setManageBonus(int manageBonus) {
		this.manageBonus = manageBonus;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public float getVciRatio() {
		return vciRatio;
	}

	public void setVciRatio(float vciRatio) {
		this.vciRatio = vciRatio;
	}

	public int getVciType() {
		return vciType;
	}

	public void setVciType(int vciType) {
		this.vciType = vciType;
	}

	public float getTciRatio() {
		return tciRatio;
	}

	public void setTciRatio(float tciRatio) {
		this.tciRatio = tciRatio;
	}

	public int getTciType() {
		return tciType;
	}

	public void setTciType(int tciType) {
		this.tciType = tciType;
	}

	public int getTagMod() {
		return tagMod;
	}

	public void setTagMod(int tagMod) {
		this.tagMod = tagMod;
	}

	
}
