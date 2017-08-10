package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.VehicleBizType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.Consts;

public class PoundageCoefficientsParam extends EmployeeParam {

	private static final long serialVersionUID = -1366507633291810740L;

	private int tid;
	@NotNull
	private String path;
	private boolean all;
	@Min(1)
	private int insurerId;
	private VehicleBizType bizType;
	private int subordinateProvince;
	
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
	
	public boolean isAll() {
		return all;
	}
	
	public void setAll(boolean all) {
		this.all = all;
	}
	
	public int getInsurerId() {
		return insurerId;
	}
	
	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}
	
	public VehicleBizType getBizType() {
		return bizType;
	}
	
	public void setBizType(VehicleBizType bizType) {
		this.bizType = bizType;
	}
	
	public int getSubordinateProvince() {
		return subordinateProvince;
	}
	
	public void setSubordinateProvince(int subordinateProvince) {
		this.subordinateProvince = subordinateProvince;
	}
	
	public String getId() {
		return tid + Consts.SYMBOL_UNDERLINE + insurerId;
	}
}
