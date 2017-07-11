package org.btkj.user.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

/**
 * 管理佣金设置
 * 
 * @author ahab
 */
public class BonusManageSettings implements UniqueModel<String> {

	private static final long serialVersionUID = 125714925701847431L;

	private String key;
	private int tid;
	private int type;
	private int rate;
	private int created;

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String key() {
		return this.key;
	}
}
