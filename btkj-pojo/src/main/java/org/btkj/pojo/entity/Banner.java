package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Banner implements UniqueModel<Integer> {

	private static final long serialVersionUID = -6109714860502169183L;
	
	private int id;
	private int appId;
	private int tid;
	private int idx;
	private String image;
	private String link;
	private int created;
	private int update;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
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

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
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
