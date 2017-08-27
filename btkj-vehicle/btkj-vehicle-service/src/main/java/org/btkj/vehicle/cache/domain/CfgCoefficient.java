package org.btkj.vehicle.cache.domain;

import java.util.Set;

import org.btkj.vehicle.pojo.enums.PoundageCoefficientType;
import org.rapid.util.common.model.UniqueModel;

public class CfgCoefficient implements UniqueModel<Integer> {

	private static final long serialVersionUID = 8738758378792751398L;

	private int id;
	private String name;
	private int maxRanges;					// 最大范围条数
	private boolean custom;
	private PoundageCoefficientType type;
	private Set<Integer> cfgCoefficientRangeIds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public PoundageCoefficientType getType() {
		return type;
	}

	public void setType(PoundageCoefficientType type) {
		this.type = type;
	}

	public Set<Integer> getCfgCoefficientRangeIds() {
		return cfgCoefficientRangeIds;
	}

	public void setCfgCoefficientRangeIds(Set<Integer> cfgCoefficientRangeIds) {
		this.cfgCoefficientRangeIds = cfgCoefficientRangeIds;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
