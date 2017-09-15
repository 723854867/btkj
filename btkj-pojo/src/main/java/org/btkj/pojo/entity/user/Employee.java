package org.btkj.pojo.entity.user;

import org.rapid.util.common.model.UniqueModel;

/**
 * 
 * @author ahab
 */
public class Employee implements UniqueModel<Integer> {

	private static final long serialVersionUID = -315073031541553807L;

	private int id;
	private int uid;
	private int tid;
	private int appId;
	private int parentId;
	private String relationPath;
	private int left;
	private int right;
	private int layer;
	private int mod;
	private int commercialRate;
	private int compulsoryRate;
	private int created;
	private int updated;
	
	public enum Mod {
		BONUS_SCALE(1),				// 规模佣金
		BONUS_MANAGE(2),			// 管理佣金
		BONUS_NPC(4),				// 非营利客车
		BONUS_NPT(8),				// 非营利货车
		BONUS_PC(16),				// 营利客车
		BONUS_PT(32),				// 营利货车
		BONUS_OTHER(64),			// 其他车
		PAY_FULL(128),				// 全额支付
		PAY_NET(256),				// 净保费支付
		PAY_ADVANCE(512),			// 公司垫付
		SEAL(1024);					// 禁用
		private static final int MOD_1 = 255;
		private static final int MOD_2 = 383;
		private static final int MOD_3 = 639;
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
		public static final boolean check(int mod) {
			return (MOD_1 & mod) == mod
					|| (MOD_2 & mod) == mod
					|| (MOD_3 & mod) == mod;
			
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getRelationPath() {
		return relationPath;
	}
	
	public void setRelationPath(String relationPath) {
		this.relationPath = relationPath;
	}
	
	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getRight() {
		return right;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	public int getCommercialRate() {
		return commercialRate;
	}
	
	public void setCommercialRate(int commercialRate) {
		this.commercialRate = commercialRate;
	}
	
	public int getCompulsoryRate() {
		return compulsoryRate;
	}
	
	public void setCompulsoryRate(int compulsoryRate) {
		this.compulsoryRate = compulsoryRate;
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
		return id;
	}
}
