package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class NonAutoBind implements UniqueModel<Integer> {

	private static final long serialVersionUID = -7604535648039465818L;

	private int id;
	private int tid;
	private int cid;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
