package org.btkj.pojo.info;

import java.io.Serializable;

/**
 * 申请信息
 * 
 * @author ahab
 *
 */
public class ApplyInfo implements Serializable {

	private static final long serialVersionUID = -8961196986907792398L;

	private int tid;				// 申请代理公司的 tid
	private int chief;				// 邀请者 uid
	private int time;				// 申请时间
	
	// 保途 app 需要改字段
	private int uid;
	
	// 独立 app 需要下面这两个字段
	private String name;
	private String identity;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getChief() {
		return chief;
	}
	
	public void setChief(int chief) {
		this.chief = chief;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
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
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
