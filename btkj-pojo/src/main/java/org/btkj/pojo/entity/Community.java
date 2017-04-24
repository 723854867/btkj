package org.btkj.pojo.entity;

import org.rapid.util.common.model.UniqueModel;

public class Community implements UniqueModel<Integer> {

	private static final long serialVersionUID = -2559361347627793765L;
	
	private int appId;
	private String image;
	private int created;
	private int updated;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		return this.appId;
	}
}
