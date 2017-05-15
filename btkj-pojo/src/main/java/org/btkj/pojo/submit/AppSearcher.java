package org.btkj.pojo.submit;

public class AppSearcher extends Page {

	private static final long serialVersionUID = -7144922898174024077L;

	private Integer id;
	private String name;
	private String mobile;
	private Integer permitNum;
	private Integer expireDate;
	private Integer state;
	private Integer order;
	private Integer asc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getPermitNum() {
		return permitNum;
	}
	public void setPermitNum(Integer permitNum) {
		this.permitNum = permitNum;
	}
	public Integer getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Integer expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
