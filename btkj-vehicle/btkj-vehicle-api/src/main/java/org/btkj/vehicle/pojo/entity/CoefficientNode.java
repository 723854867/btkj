package org.btkj.vehicle.pojo.entity;

import java.util.Set;

import org.btkj.vehicle.pojo.enums.CoefficientType;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.math.tree.NodeImpl;

import com.google.gson.annotations.Expose;

public class CoefficientNode extends NodeImpl<Integer> implements UniqueModel<Integer> {

	private static final long serialVersionUID = -2459021872559006748L;
	
	@Expose
	private boolean custom;
	@Expose
	private int maxmiumCustom;
	private CoefficientType type;
	private Set<Integer> coefficientRanges;
	
	public boolean isCustom() {
		return custom;
	}
	
	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	
	public int getMaxmiumCustom() {
		return maxmiumCustom;
	}
	
	public void setMaxmiumCustom(int maxmiumCustom) {
		this.maxmiumCustom = maxmiumCustom;
	}
	
	public CoefficientType getType() {
		return type;
	}
	
	public void setType(CoefficientType type) {
		this.type = type;
	}
	
	public Set<Integer> getCoefficientRanges() {
		return coefficientRanges;
	}
	
	public void setCoefficientRanges(Set<Integer> coefficientRanges) {
		this.coefficientRanges = coefficientRanges;
	}
	
	@Override
	public Integer key() {
		return this.id;
	}
}
