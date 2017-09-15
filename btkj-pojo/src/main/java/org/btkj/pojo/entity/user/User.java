package org.btkj.pojo.entity.user;

import org.rapid.util.common.model.UniqueModel;

public class User implements UniqueModel<Integer> {

	private static final long serialVersionUID = 524781447550935468L;
	
	private int uid;
	private int appId;
	private String mobile;
	private String pwd;
	private String name;
	private String avatar;
	private String identity;
	private String identityFace;
	private String identityBack;
	private int appLoginTime;			
	private int pcLoginTime;
	private int mod;
	private int created;
	private int updated;
	
	public enum Mod {
		TOP_ROLE(1),
		SEAL(2);
		private int mark;
		private Mod(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public boolean satisfy(int cmod) {
			return (cmod & mark) == mark;
		}
	}

	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	
	public int getAppLoginTime() {
		return appLoginTime;
	}
	
	public void setAppLoginTime(int appLoginTime) {
		this.appLoginTime = appLoginTime;
	}
	
	public int getPcLoginTime() {
		return pcLoginTime;
	}
	
	public void setPcLoginTime(int pcLoginTime) {
		this.pcLoginTime = pcLoginTime;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return uid;
	}
}
