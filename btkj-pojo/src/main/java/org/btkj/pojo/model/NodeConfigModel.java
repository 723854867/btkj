package org.btkj.pojo.model;

import java.io.Serializable;
import java.util.Map;

import org.btkj.pojo.entity.vehicle.PoundageConfig.NodeConfig;

public class NodeConfigModel implements Serializable {

	private static final long serialVersionUID = 8482817679443350662L;

	private int cmRate;
	private int cpRate;
	private int cmRetainRate;
	private int cpRetainRate;
	private boolean effective;
	private Map<Integer, Integer> ratios;
	
	public NodeConfigModel() {}
	
	public NodeConfigModel(NodeConfig config, Map<Integer, Integer> ratios) {
		this.cmRate = config.getCmRate();
		this.cpRate = config.getCpRate();
		this.cmRetainRate = config.getCmRetainRate();
		this.cpRetainRate = config.getCpRetainRate();
		this.effective = config.isEffective();
		this.ratios = ratios;
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

	public Map<Integer, Integer> getRatios() {
		return ratios;
	}

	public void setRatios(Map<Integer, Integer> ratios) {
		this.ratios = ratios;
	}
}
