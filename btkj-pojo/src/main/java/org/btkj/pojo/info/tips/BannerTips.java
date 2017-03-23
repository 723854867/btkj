package org.btkj.pojo.info.tips;

import java.io.Serializable;

/**
 * banner tips，只显示：idx、image
 * 
 * @author ahab
 *
 */
public class BannerTips implements Serializable {

	private static final long serialVersionUID = -3151358008948855249L;

	private int idx;
	private String image;
	
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
}
