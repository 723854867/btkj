package org.btkj.pojo.info;

import java.io.Serializable;

import org.btkj.pojo.info.mainpage.ILoginInfo;

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
	
	/**
	 * 独立 app 在登录时如果用户不存在会判断该账号是否已经有申请正在审核，如果有则显示的时审核信息，而不是登录或者主页信息
	 * 
	 * @author ahab
	 *
	 */
	public static class ApplyChecker extends ApplyInfo implements ILoginInfo {

		private static final long serialVersionUID = -7130338155291179327L;
		
		public ApplyChecker() {}
		
		public ApplyChecker(ApplyInfo ai) {
			setTid(ai.getTid());
			setChief(ai.getChief());
			setTime(ai.getTime());
			setUid(ai.getUid());
			setName(ai.getName());
			setIdentity(ai.getIdentity());
		}
		
		@Override
		public String getToken() {
			return null;
		}
		@Override
		public void setToken(String token) {
		}
	}
}
