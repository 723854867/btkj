package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.btkj.vehicle.pojo.enums.PoundageCoefficientType;

public class CoefficientStructure implements Serializable {

	private static final long serialVersionUID = 6814747413702038623L;

	private int id;
	private Map<Integer, CoefficientNode> nodes;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Map<Integer, CoefficientNode> getNodes() {
		return nodes;
	}
	
	public void setNodes(Map<Integer, CoefficientNode> nodes) {
		this.nodes = nodes;
	}

	public static class CoefficientNode implements Serializable {

		private static final long serialVersionUID = -7700644286318575107L;

		private String name;
		private int maxRanges;
		private boolean custom;
		private int cfgCoefficientId;
		private PoundageCoefficientType type;
		private Map<Integer, CoefficientRange> ranges;
		private Map<Integer, CoefficientNode> children;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public int getMaxRanges() {
			return maxRanges;
		}
		
		public void setMaxRanges(int maxRanges) {
			this.maxRanges = maxRanges;
		}

		public boolean isCustom() {
			return custom;
		}

		public void setCustom(boolean custom) {
			this.custom = custom;
		}

		public int getCfgCoefficientId() {
			return cfgCoefficientId;
		}

		public void setCfgCoefficientId(int cfgCoefficientId) {
			this.cfgCoefficientId = cfgCoefficientId;
		}

		public PoundageCoefficientType getType() {
			return type;
		}
		
		public void setType(PoundageCoefficientType type) {
			this.type = type;
		}
		
		public Map<Integer, CoefficientRange> getRanges() {
			return ranges;
		}

		public void setRanges(Map<Integer, CoefficientRange> ranges) {
			this.ranges = ranges;
		}

		public Map<Integer, CoefficientNode> getChildren() {
			return children;
		}

		public void setChildren(Map<Integer, CoefficientNode> children) {
			this.children = children;
		}
		
		public void addChild(CoefficientNode node) {
			if (null == this.children)
				this.children = new HashMap<Integer, CoefficientNode>();
			this.children.put(node.getCfgCoefficientId(), node);
		}
	}
}
