package org.btkj.bihu.vehicle.mybatis.entity;

import org.rapid.util.common.model.UniqueModel;

public class TenantConfig implements UniqueModel<Integer> {

	private static final long serialVersionUID = 8715065538134036693L;

	private int tid;
	private String agent;
	private String key;
	private int created;
	private int updated;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
