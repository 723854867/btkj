package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.pojo.enums.OrderFilterState;
import org.rapid.util.validator.custom.CarLicense;

public class VehicleOrdersParam extends EmployeeParam {

	private static final long serialVersionUID = -1996436498216211489L;

	@Min(1)
	private Integer appId;
	@Min(1)
	private Integer uid;
	@Min(1)
	private Integer tid;
	@CarLicense
	private String license;
	@Min(1)
	private Integer tarId;
	private OrderFilterState state;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getLicense() {
		return license;
	}
	
	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getTarId() {
		return tarId;
	}

	public void setTarId(Integer tarId) {
		this.tarId = tarId;
	}

	public OrderFilterState getState() {
		return state;
	}

	public void setState(OrderFilterState state) {
		this.state = state;
	}
}
