package org.btkj.pojo.submit;

public class UserSearcher extends Page {

	private static final long serialVersionUID = -7144922898174024077L;

	private Integer uid;
	private String mobile;
	private Integer order;
	private Integer asc;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getAsc() {
		return asc;
	}
	public void setAsc(Integer asc) {
		this.asc = asc;
	}

}
