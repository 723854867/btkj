package org.btkj.vehicle.pojo.submit;

import org.btkj.pojo.enums.BonusScaleType;
import org.btkj.pojo.enums.PolicyNature;
import org.btkj.pojo.vo.Page;
import org.btkj.vehicle.pojo.VehiclePolicyType;

public class VehiclePolicySearcher extends Page {

	private static final long serialVersionUID = 2310017996464292933L;

	private Integer tid;					// 商户ID
	private Integer uid;					// 用户ID
	private Integer employeeId;				// 雇员id
	private Integer insurerId;				// 险企ID
	private String license;					// 车牌号
	private String owner;					// 车主姓名
	private String salesman;				// 业务员姓名
	private VehiclePolicyType type;			// 保单类型
	private BonusScaleType scaleType;		// 规模奖励类型
	private Boolean transfer;				// 是否过户车
	private PolicyNature nature;			// 转续保状态

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(Integer insurerId) {
		this.insurerId = insurerId;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public VehiclePolicyType getType() {
		return type;
	}

	public void setType(VehiclePolicyType type) {
		this.type = type;
	}

	public BonusScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(BonusScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public Boolean getTransfer() {
		return transfer;
	}

	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}

	public PolicyNature getNature() {
		return nature;
	}

	public void setNature(PolicyNature nature) {
		this.nature = nature;
	}
}
