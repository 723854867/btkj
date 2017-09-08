package org.btkj.pojo.entity.vehicle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public class PoundageConfig implements UniqueModel<String> {

	private static final long serialVersionUID = -2255766443601366474L;

	private String _id;
	private int tid;
	private Map<Integer, Map<Integer, NodeConfig>> configs;
	
	public PoundageConfig() {}
	
	public PoundageConfig(int tid) {
		this.tid = tid;
		this._id = String.valueOf(tid);
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
	
	public Map<Integer, Map<Integer, NodeConfig>> getConfigs() {
		return configs;
	}
	
	public void setConfigs(Map<Integer, Map<Integer, NodeConfig>> configs) {
		this.configs = configs;
	}
	
	public static class NodeConfig implements Serializable {
		private static final long serialVersionUID = -8027714772631083070L;
		private int cmRate;
		private int cpRate;
		private int cmRetainRate;
		private int cpRetainRate;
		private boolean effective;
		private Map<Integer, Map<Integer, Integer>> ratios;
		public NodeConfig() {
			this.effective = true;
		}
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
		public boolean isEffective() {
			return effective;
		}
		public void setEffective(boolean effective) {
			this.effective = effective;
		}
		public Map<Integer, Map<Integer, Integer>> getRatios() {
			return ratios;
		}
		public void setRatios(Map<Integer, Map<Integer, Integer>> ratios) {
			this.ratios = ratios;
		}
		public void addRatio(int coefficientId, int rangeId, int ratio) { 
			if (null == this.ratios)
				this.ratios = new HashMap<Integer, Map<Integer, Integer>>();
			Map<Integer, Integer> map = this.ratios.get(coefficientId);
			if (null == map) {
				map = new HashMap<Integer, Integer>();
				this.ratios.put(coefficientId, map);
			}
			map.put(rangeId, ratio);
		}
	}
	
	@Override
	public String key() {
		return this._id;
	}
}
