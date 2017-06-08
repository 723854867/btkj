package org.btkj.courier.pojo;

import java.io.Serializable;

import org.btkj.pojo.entity.User;

public class JianJieUser implements Serializable {

	private static final long serialVersionUID = 6115356878816218781L;

	private String LoginName;
	private String ChineseName;
	private String IdentityNo;
	private String Phone;
	
	public JianJieUser(User user) {
		this.LoginName = user.getName();
		this.ChineseName = user.getName();
		this.IdentityNo = user.getIdentity();
		this.Phone = user.getMobile();
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getChineseName() {
		return ChineseName;
	}

	public void setChineseName(String chineseName) {
		ChineseName = chineseName;
	}

	public String getIdentityNo() {
		return IdentityNo;
	}

	public void setIdentityNo(String identityNo) {
		IdentityNo = identityNo;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
}
