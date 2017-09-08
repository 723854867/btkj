package org.btkj.pojo.entity.vehicle;

import org.rapid.util.common.model.UniqueModel;

public class BonusScaleConfig implements UniqueModel<Integer> {

	private static final long serialVersionUID = -5486050066695175759L;

	private int id;
	private int tid;
	private int rate;
	private int comparison;
	private String comparableValue;
	private int created;
	private int updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getComparison() {
		return comparison;
	}
	
	public void setComparison(int comparison) {
		this.comparison = comparison;
	}
	
	public String getComparableValue() {
		return comparableValue;
	}
	
	public void setComparableValue(String comparableValue) {
		this.comparableValue = comparableValue;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
