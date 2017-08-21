package org.btkj.master.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Admin implements UniqueModel<Integer> {

	private static final long serialVersionUID = 6721661859589463248L;

	private int id;
	private String pwd;
	private String name;
	private int mod;
	private int created;
	private int updated;
	
	public enum Mod {
		FULL_PRIVILEGES(1);					// 拥有保途所有权限
		private int mark;
		private Mod(int mark) {
			this.mark = mark;
		}
		public int mark() {
			return mark;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return this.id;
	}
}
