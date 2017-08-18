package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.AliyunUtil;
import org.btkj.pojo.entity.UserPO;
import org.btkj.pojo.info.UserTips;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -8394028501904858733L;

	private String token;
	private UserDetailTips user;
	
	public LoginInfo() {}
	
	public LoginInfo(String token, UserPO user) {
		this.token = token;
		this.user = new UserDetailTips(user);
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserTips getUser() {
		return user;
	}
	
	public void setUser(UserDetailTips user) {
		this.user = user;
	}
	
	private class UserDetailTips extends UserTips {
		private static final long serialVersionUID = 5745545300547462871L;
		private String identity;
		private String identityFace;
		private String identityBack;
		public UserDetailTips() {}
		public UserDetailTips(UserPO user) {
			super(user);
			this.identity = user.getIdentity();
			if (null != user.getIdentityFace())
				this.identityFace = AliyunUtil.userResource(user, user.getIdentityFace());
			if (null != user.getIdentityBack())
				this.identityBack = AliyunUtil.userResource(user, user.getIdentityBack());
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
	}
}
