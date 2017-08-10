package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.bo.BonusRouteBody;
import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.Consts;

public class BonusPoundageEditParam extends EmployeeParam {

	private static final long serialVersionUID = 2678424434239627957L;

	private int tid;
	@NotNull
	private String path;
	@Min(1)
	private int insurerId;
	private boolean delete;
	private VehicleBizType bizType;
	private BonusRouteBody routeBody;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public boolean isDelete() {
		return delete;
	}
	
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
	public BonusRouteBody getRouteBody() {
		return routeBody;
	}
	
	public void setRouteBody(BonusRouteBody routeBody) {
		this.routeBody = routeBody;
	}
	
	public VehicleBizType getBizType() {
		return bizType;
	}
	
	public void setBizType(VehicleBizType bizType) {
		this.bizType = bizType;
	}
	
	public String getId() {
		return tid + Consts.SYMBOL_UNDERLINE + insurerId;
	}
}
