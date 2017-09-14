package org.btkj.pojo.info.user;

import java.io.Serializable;

import org.btkj.pojo.AliyunResourceUtil;
import org.btkj.pojo.entity.user.AppPO;
import org.btkj.pojo.entity.user.UserPO;
import org.btkj.pojo.info.UserTips;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -8394028501904858733L;

	private String token;
	private UserDetailTips user;
	
	public LoginInfo() {}
	
	public LoginInfo(AppPO app, UserPO user, String token) {
		this.token = token;
		this.user = new UserDetailTips(app, user);
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
		public UserDetailTips(AppPO app, UserPO user) {
			super(app, user);
			this.identity = user.getIdentity();
			if (null != user.getIdentityFace())
				this.identityFace = AliyunResourceUtil.userResource(app, user, user.getIdentityFace());
			if (null != user.getIdentityBack())
				this.identityBack = AliyunResourceUtil.userResource(app, user, user.getIdentityBack());
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
