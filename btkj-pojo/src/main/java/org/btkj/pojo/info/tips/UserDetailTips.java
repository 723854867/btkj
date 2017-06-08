package org.btkj.pojo.info.tips;

import org.btkj.pojo.AliyunUtil;
import org.btkj.pojo.entity.User;

public class UserDetailTips extends UserTips {

	private static final long serialVersionUID = -1502348038421880781L;

	private String identity;
	private String identityFace;
	private String identityBack;
	
	public UserDetailTips() {}
	
	public UserDetailTips(User user) {
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
