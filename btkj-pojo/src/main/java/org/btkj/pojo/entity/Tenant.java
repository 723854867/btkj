package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;

public class Tenant implements Entity<Integer> {

	private static final long serialVersionUID = 9101905059642013405L;

	private int tid;
	private String name;
	private int appId;
	private int regionId;
	private int mod;
	private String privilege;
	private String pwd;
	private int created;
	private int updated;
	
	public enum Mod {
		/**
		 * 是否是独立 app 的租户，如果为 0 表示保途的租户
		 */
		ISOLATE(1);
		private int mark;
		private Mod(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
		public boolean hit(int mod) {
			return (this.mark & mod) == this.mark;
		}
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
		return tid;
	}
}
