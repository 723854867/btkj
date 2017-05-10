package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.Employee;
import org.btkj.pojo.entity.User;

/**
 * 员工信息
 * 
 * @author sj
 *
 */
public class EmoloyeeInfo implements Serializable {
	private static final long serialVersionUID = -2999062720198009694L;
	
	private int id;						//邀请码(员工工号)
	private int uid;
	private int tid;
	private String name;
	private int parentId;				//邀请人id
	private String parentName;			//邀请人姓名
	private String mobile;				//电话（账号）
	private int created;				//注册时间
	private int payType;				//支付方式
	private int state;					//状态
	
	public EmoloyeeInfo(){};
	
	public EmoloyeeInfo(Employee employee, User user){
		this.id = employee.getId();
		this.tid = employee.getTid();
		this.uid  = employee.getUid();
		this.parentId = employee.getParentId();
		this.name = employee.getName();
		this.mobile = user.getMobile();
		this.created = employee.getCreated();
		this.payType = employee.getPayType();
		this.state=employee.getState();
	};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
