package org.btkj.pojo.vo;

import org.rapid.util.common.model.UniqueModel;

/**
 * 申请信息
 * 
 * @author ahab
 *
 */
public class ApplyInfo implements UniqueModel<String> {

	private static final long serialVersionUID = -8961196986907792398L;

	private int tid; 				// 申请代理公司的 tid
	private int uid; 				// 申请者uid
	private int chief; 				// 邀请者雇员ID
	private int time; 				// 申请时间
	private int chiefUid;			// 邀请者用户ID

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
	
	public int getChiefUid() {
		return chiefUid;
	}
	
	public void setChiefUid(int chiefUid) {
		this.chiefUid = chiefUid;
	}

	@Override
	public String key() {
		return tid + "-" + uid;
	}
}
