package org.btkj.vehicle.pojo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public class PoundageConfig implements UniqueModel<String> {

	private static final long serialVersionUID = -2255766443601366474L;

	private String _id;
	private int tid;
	private int insurerId;
	private Map<Integer, MirrorPoundageNode> structure;
	
	public PoundageConfig() {}
	
	public PoundageConfig(int tid, int insurerId) {
		this.tid = tid;
		this.insurerId = insurerId;
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
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
	
	public Map<Integer, MirrorPoundageNode> getStructure() {
		return structure;
	}
	
	public void setStructure(Map<Integer, MirrorPoundageNode> structure) {
		this.structure = structure;
	}
	
	@Override
	public String key() {
		return this._id;
	}
	
	/**
	 * 节点的镜像
	 * 
	 * @author ahab
	 */
	public static class MirrorPoundageNode implements Serializable {
		private static final long serialVersionUID = -5220776473827685585L;
		private int commercialRate;
		private int compulsoryRate;
		private int cfgPoundageNodeId;
		private int commercialRetainRate;
		private int compulsoryRetainRate;
		private boolean coefficientsEffective;				// 关联的系数是否生效
		private Map<Integer, MirrorPoundageNode> children;
		private Map<Integer, MirrorCoefficient> coefficients;
		public int getCommercialRate() {
			return commercialRate;
		}
		public void setCommercialRate(int commercialRate) {
			this.commercialRate = commercialRate;
		}
		public int getCompulsoryRate() {
			return compulsoryRate;
		}
		public void setCompulsoryRate(int compulsoryRate) {
			this.compulsoryRate = compulsoryRate;
		}
		public int getCfgPoundageNodeId() {
			return cfgPoundageNodeId;
		}
		public void setCfgPoundageNodeId(int cfgPoundageNodeId) {
			this.cfgPoundageNodeId = cfgPoundageNodeId;
		}
		public int getCommercialRetainRate() {
			return commercialRetainRate;
		}
		public void setCommercialRetainRate(int commercialRetainRate) {
			this.commercialRetainRate = commercialRetainRate;
		}
		public int getCompulsoryRetainRate() {
			return compulsoryRetainRate;
		}
		public void setCompulsoryRetainRate(int compulsoryRetainRate) {
			this.compulsoryRetainRate = compulsoryRetainRate;
		}
		public boolean isCoefficientsEffective() {
			return coefficientsEffective;
		}
		public void setCoefficientsEffective(boolean coefficientsEffective) {
			this.coefficientsEffective = coefficientsEffective;
		}
		public Map<Integer, MirrorPoundageNode> getChildren() {
			return children;
		}
		public Map<Integer, MirrorCoefficient> getCoefficients() {
			return coefficients;
		}
		public void setCoefficients(Map<Integer, MirrorCoefficient> coefficients) {
			this.coefficients = coefficients;
		}
		public void setChildren(Map<Integer, MirrorPoundageNode> children) {
			this.children = children;
		}
		public void addChild(MirrorPoundageNode node) {
			if (null == this.children)
				this.children = new HashMap<Integer, MirrorPoundageNode>();
			this.children.put(node.getCfgPoundageNodeId(), node);
		}
		public void removeChild(MirrorPoundageNode node) {
			this.children.remove(node.getCfgPoundageNodeId());
		}
	}
	public static class MirrorCoefficient implements Serializable {
		private static final long serialVersionUID = 1110008428266690080L;
		private int id;
		private Map<Integer, Integer> ratios;
		private Map<Integer, MirrorCoefficient> children;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Map<Integer, Integer> getRatios() {
			return ratios;
		}
		public void setRatios(Map<Integer, Integer> ratios) {
			this.ratios = ratios;
		}
		public Map<Integer, MirrorCoefficient> getChildren() {
			return children;
		}
		public void setChildren(Map<Integer, MirrorCoefficient> children) {
			this.children = children;
		}
	}
}
