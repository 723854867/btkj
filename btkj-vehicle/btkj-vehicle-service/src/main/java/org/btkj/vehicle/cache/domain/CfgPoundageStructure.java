package org.btkj.vehicle.cache.domain;

import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public class CfgPoundageStructure implements UniqueModel<Integer> {

	private static final long serialVersionUID = 8037316009814288637L;
	
	private int cfgPoundageNodeId;
	private int cfgCoefficientStructureId;
	private Map<Integer, CfgPoundageStructure> children;
	
	public int getCfgPoundageNodeId() {
		return cfgPoundageNodeId;
	}
	
	public void setCfgPoundageNodeId(int cfgPoundageNodeId) {
		this.cfgPoundageNodeId = cfgPoundageNodeId;
	}
	
	public int getCfgCoefficientStructureId() {
		return cfgCoefficientStructureId;
	}
	
	public void setCfgCoefficientStructureId(int cfgCoefficientStructureId) {
		this.cfgCoefficientStructureId = cfgCoefficientStructureId;
	}
	
	public Map<Integer, CfgPoundageStructure> getChildren() {
		return children;
	}
	
	public void setChildren(Map<Integer, CfgPoundageStructure> children) {
		this.children = children;
	}

	@Override
	public Integer key() {
		return cfgPoundageNodeId;
	}
}
