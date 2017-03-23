package org.btkj.pojo.entity;

import org.rapid.data.storage.db.Entity;

public class Banner implements Entity<Integer> {

	private static final long serialVersionUID = -6109714860502169183L;
	
	private int id;
	private int tid;
	private int idx;
	private String url;
	private int created;
	private int update;

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

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}

	@Override
	public Integer key() {
		return id;
	}
}
