package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.pojo.OrderFilterState;

public class VehicleOrdersParam extends EmployeeParam {

	private static final long serialVersionUID = -1996436498216211489L;

	@Min(1)
	private Integer appId;
	@Min(1)
	private Integer uid;
	@Min(1)
	private Integer tid;
	@Size(min = 1, max = 20)
	private String batchId;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
