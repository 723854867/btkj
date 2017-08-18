package org.btkj.pojo.model;

import java.util.Map;

import org.rapid.util.Node;

/**
 * 佣金节点数据提
 * 
 * @author ahab
 */
public class BonusRouteBody extends Node<BonusRouteBody> {

	private int commercialRate;
	private int compulsoryRate;
	private int commercialRetainRate;
	private int compulsoryRetainRate;
	private Map<Integer, Integer> commercialCommisionSpinner;
	
	public BonusRouteBody() {}
	
	public BonusRouteBody(String id, String title) {
		super(id, title);
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

	public Map<Integer, Integer> getCommercialCommisionSpinner() {
		return commercialCommisionSpinner;
	}

	public void setCommercialCommisionSpinner(Map<Integer, Integer> commercialCommisionSpinner) {
		this.commercialCommisionSpinner = commercialCommisionSpinner;
	}
	
	public boolean isValid() {
		return 0 != commercialRate || 0 != compulsoryRate || 0 != commercialRetainRate || 0 != compulsoryRetainRate
				|| (null != commercialCommisionSpinner && !commercialCommisionSpinner.isEmpty());
	}
}
