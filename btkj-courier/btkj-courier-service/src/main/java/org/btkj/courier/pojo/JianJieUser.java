package org.btkj.courier.pojo;

import java.io.Serializable;

import org.btkj.pojo.po.UserPO;

public class JianJieUser implements Serializable {

	private static final long serialVersionUID = 6115356878816218781L;

	private String LoginName;
	private String ChineseName;
	private String IdentityNo;
	
	public JianJieUser(UserPO user) {
		this.LoginName = String.valueOf(user.getUid());
		this.ChineseName = user.getName();
		this.IdentityNo = user.getIdentity();
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
}
