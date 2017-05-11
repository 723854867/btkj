package org.btkj.pojo.submit;

public class EmployeeSearcher extends Page {

	private static final long serialVersionUID = -7144922898174024077L;

	private Integer tid;
	private Integer employeeId;
	private String name;
	private String mobile;
	private Integer tagMod;
	private Integer payType;
	private Integer order;
	private Integer asc;

	public Integer getTid() {
		return tid;
	}
	
	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public Integer getTagMod() {
		return tagMod;
	}

	public void setTagMod(Integer tagMod) {
		this.tagMod = tagMod;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
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
