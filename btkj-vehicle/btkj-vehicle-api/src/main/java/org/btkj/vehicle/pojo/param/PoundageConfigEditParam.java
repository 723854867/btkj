package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;
import org.btkj.vehicle.pojo.entity.PoundageConfig.NodeConfig;

public class PoundageConfigEditParam extends EmployeeParam {

	private static final long serialVersionUID = -6275189674984437228L;

	private int tid;
	@Min(1)
	private int insurerId;
	@Min(1)
	private int nodeId;
	@NotNull
	private Type type;
	private NodeConfig config;
	
	public enum Type {
		EDIT,
		BIND,
		UNBIND;
	}
	
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
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public NodeConfig getConfig() {
		return config;
	}
	
	public void setConfig(NodeConfig config) {
		this.config = config;
	}
}
