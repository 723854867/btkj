package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.Banner;

/**
 * banner tips，只显示：idx、image
 * 
 * @author ahab
 */
public class BannerTips implements Serializable {

	private static final long serialVersionUID = -3151358008948855249L;

	private int id;
	private int idx;
	private String image;
	private String link;
	
	public BannerTips() {}
	
	public BannerTips(Banner banner) {
		this.id = banner.getId();
		this.idx = banner.getIdx();
		this.image = banner.getImage();
		this.link = banner.getLink();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
}
