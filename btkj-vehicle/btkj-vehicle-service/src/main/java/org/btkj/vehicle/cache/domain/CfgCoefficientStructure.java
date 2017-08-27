package org.btkj.vehicle.cache.domain;

import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public class CfgCoefficientStructure implements UniqueModel<Integer> {
	
	private static final long serialVersionUID = -6949267265020465746L;
	
	private int id;
	private Map<Integer, CfgCoefficientNode> nodes;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Map<Integer, CfgCoefficientNode> getNodes() {
		return nodes;
	}
	
	public void setNodes(Map<Integer, CfgCoefficientNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Integer key() {
		return this.id;
	}
	
	public class CfgCoefficientNode {
		private int cfgCoefficientId;
		private Map<Integer, CfgCoefficientNode> children;
		public int getCfgCoefficientId() {
			return cfgCoefficientId;
		}
		public void setCfgCoefficientId(int cfgCoefficientId) {
			this.cfgCoefficientId = cfgCoefficientId;
		}
		public Map<Integer, CfgCoefficientNode> getChildren() {
			return children;
		}
		public void setChildren(Map<Integer, CfgCoefficientNode> children) {
			this.children = children;
		}
	}
}
