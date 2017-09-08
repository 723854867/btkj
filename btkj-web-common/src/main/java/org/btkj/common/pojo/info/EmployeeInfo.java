package org.btkj.common.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.entity.EmployeePO;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.entity.EmployeePO.Mod;
import org.btkj.pojo.enums.PayType;

public class EmployeeInfo implements Serializable {

	private static final long serialVersionUID = -4607104983280138052L;

	private int uid;
	private int employeeId;
	private String avatar;
	private String name;
	private String mobile;
	private String identity;
	private String identityFace;
	private String identityBack;
	private PayType payType;
	
	public EmployeeInfo(UserPO user, EmployeePO employee) {
		this.uid = user.getUid();
		this.employeeId = employee.getId();
		this.avatar = user.getAvatar();
		this.name = user.getName();
		this.mobile = user.getMobile();
		this.identity = user.getIdentity();
		this.identityFace = user.getIdentityFace();
		this.identityBack = user.getIdentityBack();
		if ((Mod.PAY_NET.mark() & employee.getMod()) == Mod.PAY_NET.mark())
			this.payType = PayType.PAY_NET;
		else if ((Mod.PAY_ADVANCE.mark() & employee.getMod()) == Mod.PAY_ADVANCE.mark())
			this.payType = PayType.PAY_ADVANCE;
		else 
			this.payType = PayType.PAY_FULL;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIdentityFace() {
		return identityFace;
	}

	public void setIdentityFace(String identityFace) {
		this.identityFace = identityFace;
	}

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public PayType getPayType() {
		return payType;
	}
	
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
}
