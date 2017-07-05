package org.btkj.pojo.submit;

import org.btkj.pojo.enums.vehicle.VehicleBizType;
import org.btkj.pojo.model.insur.vehicle.BonusRouteBody;
import org.rapid.util.common.Consts;

public class BonusSearcher {

	private int tid;
	private int subordinateProvince;			// 所属省份行政区划代码
	private String path;
	private int insurerId;
	private boolean delete;
	private BonusRouteBody routeBody;
	
	private VehicleBizType bizType;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getSubordinateProvince() {
		return subordinateProvince;
	}
	
	public void setSubordinateProvince(int subordinateProvince) {
		this.subordinateProvince = subordinateProvince;
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
