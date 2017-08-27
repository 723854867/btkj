package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.util.Map;

import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorCoefficient;
import org.btkj.vehicle.pojo.entity.PoundageConfig.MirrorPoundageNode;

public class PoundageNodeConfigInfo implements Serializable {

	private static final long serialVersionUID = -6882399843378383551L;

	private int commercialRate;
	private int compulsoryRate;
	private int commercialRetainRate;
	private int compulsoryRetainRate;
	private Map<Integer, Integer> ratios;
	
	public PoundageNodeConfigInfo() {}
	
	public PoundageNodeConfigInfo(MirrorPoundageNode node, MirrorCoefficient coefficient) {
		this.commercialRate = node.getCommercialRate();
		this.compulsoryRate = node.getCompulsoryRate();
		this.commercialRetainRate = node.getCommercialRetainRate();
		this.compulsoryRetainRate = node.getCompulsoryRetainRate();
		if (null != coefficient)
			this.ratios = coefficient.getRatios();
	}

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

	public Map<Integer, Integer> getRatios() {
		return ratios;
	}

	public void setRatios(Map<Integer, Integer> ratios) {
		this.ratios = ratios;
	}
}
