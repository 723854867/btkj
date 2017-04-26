package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 员工信息
 * 
 * @author sj
 *
 */
public class EmoloyeeInfo implements Serializable {
	private static final long serialVersionUID = -2999062720198009694L;
	
	private int uid;
	private int app_id;
	private String name;
	private int tid;
	private int parent_id;				//邀请人id
	private int invitation_code;		//邀请码
	private String parent_name;			//邀请人姓名
	private String mobile;				//电话（账号）
	private String identity;
	private int created;				//注册时间
	private int mod;					//状态模值
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getInvitation_code() {
		return invitation_code;
	}
	public void setInvitation_code(int invitation_code) {
		this.invitation_code = invitation_code;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
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
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	
}
