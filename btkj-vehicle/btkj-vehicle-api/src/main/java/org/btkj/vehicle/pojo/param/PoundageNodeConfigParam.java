package org.btkj.vehicle.pojo.param;

import java.util.LinkedList;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.common.Consts;

public class PoundageNodeConfigParam extends EmployeeParam {

	private static final long serialVersionUID = 6311093891799318841L;

	private int tid;
	@Min(1)
	private int insurerId;
	@NotNull
	@Size(min = 1)
	private LinkedList<Integer> nodePath;
	private int cfgCoefficientId;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public LinkedList<Integer> getNodePath() {
		return nodePath;
	}

	public void setNodePath(LinkedList<Integer> nodePath) {
		this.nodePath = nodePath;
	}

	public int getCfgCoefficientId() {
		return cfgCoefficientId;
	}

	public void setCfgCoefficientId(int cfgCoefficientId) {
		this.cfgCoefficientId = cfgCoefficientId;
	}
	
	public String key() {
		return tid + Consts.SYMBOL_UNDERLINE + insurerId;
	}
}
