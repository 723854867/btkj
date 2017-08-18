package org.btkj.user.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.info.ApplyInfo;

public class ApplyPagingInfo implements Serializable {

	private static final long serialVersionUID = 2533276960016530300L;

	private int uid;
	private String name;
	private String mobile;
	private int parentId;
	private int parentUid;
	private String parentName;
	private String parentMobile;
	private String identity;
	private String identityFace;
	private String identityBack;
	private int time;

	public ApplyPagingInfo() {}

	public ApplyPagingInfo(ApplyInfo applyInfo) {
		this.uid = applyInfo.getUid();
		this.parentId = applyInfo.getChief();
		this.time = applyInfo.getTime();
		this.parentUid = applyInfo.getChiefUid();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getParentUid() {
		return parentUid;
	}

	public void setParentUid(int parentUid) {
		this.parentUid = parentUid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
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

	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
}
