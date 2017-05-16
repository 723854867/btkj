package org.btkj.pojo.info;

import org.rapid.util.common.model.UniqueModel;

/**
 * 申请信息
 * 
 * @author ahab
 *
 */
public class ApplyInfo implements UniqueModel<String> {

	private static final long serialVersionUID = -8961196986907792398L;

	private int tid; 					// 申请代理公司的 tid
	private int uid; 					// 申请者uid
	private String name; 				// 申请者名字
	private String mobile; 				// 申请者手机号
	private int chief; 					// 邀请者 uid
	private String chiefName; 			// 邀请者名字
	private String chiefMobile; 		// 邀请者手机号
	private int time; 					// 申请时间
	private String identity;			// 申请者身份证号
	private String identityFace;		// 身份证正面
	private String identityBack;		// 身份证反面

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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

	public int getChief() {
		return chief;
	}

	public void setChief(int chief) {
		this.chief = chief;
	}

	public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public String getChiefMobile() {
		return chiefMobile;
	}

	public void setChiefMobile(String chiefMobile) {
		this.chiefMobile = chiefMobile;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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

	@Override
	public String key() {
		return tid + "-" + uid;
	}
}
