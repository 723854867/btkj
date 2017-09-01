package org.btkj.vehicle.pojo.param;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.btkj.pojo.param.EmployeeParam;

public class PoundageConfigEditParam extends EmployeeParam {

	private static final long serialVersionUID = -6275189674984437228L;

	private int tid;
	@Min(1)
	private int insurerId;
	@Min(1)
	private int nodeId;
	private NodeConfigInfo config;
	
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
	
	public int getNodeId() {
		return nodeId;
	}
	
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public NodeConfigInfo getConfig() {
		return config;
	}
	
	public void setConfig(NodeConfigInfo config) {
		this.config = config;
	}
	
	public class NodeConfigInfo implements Serializable {
		private static final long serialVersionUID = 8759990509093971749L;
		private int cmRate;
		private int cpRate;
		private int cmRetainRate;
		private int cpRetainRate;
		private Integer coefficientId;
		private Integer rangeId;
		private int rangeRate;
		private boolean effective;
		public int getCmRate() {
			return cmRate;
		}
		public void setCmRate(int cmRate) {
			this.cmRate = cmRate;
		}
		public int getCpRate() {
			return cpRate;
		}
		public void setCpRate(int cpRate) {
			this.cpRate = cpRate;
		}
		public int getCmRetainRate() {
			return cmRetainRate;
		}
		public void setCmRetainRate(int cmRetainRate) {
			this.cmRetainRate = cmRetainRate;
		}
		public int getCpRetainRate() {
			return cpRetainRate;
		}
		public void setCpRetainRate(int cpRetainRate) {
			this.cpRetainRate = cpRetainRate;
		}
		public Integer getCoefficientId() {
			return coefficientId;
		}
		public void setCoefficientId(Integer coefficientId) {
			this.coefficientId = coefficientId;
		}
		public Integer getRangeId() {
			return rangeId;
		}
		public void setRangeId(Integer rangeId) {
			this.rangeId = rangeId;
		}
		public int getRangeRate() {
			return rangeRate;
		}
		public void setRangeRate(int rangeRate) {
			this.rangeRate = rangeRate;
		}
		public boolean isEffective() {
			return effective;
		}
		public void setEffective(boolean effective) {
			this.effective = effective;
		}
	}
}
