package org.btkj.pojo.entity.vehicle;

import java.util.Set;

import org.btkj.pojo.enums.PoundageType;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.math.compare.Comparison;
import org.rapid.util.math.tree.NodeImpl;

import com.google.gson.annotations.Expose;

public class PoundageNode extends NodeImpl<Integer> implements UniqueModel<Integer> {

	private static final long serialVersionUID = 4034992659956349660L;

	private int gid;
	@Expose
	private boolean poly;
	private int priority;
	private String[] cval;
	private PoundageType type;
	private Comparison comparison;
	private Set<Integer> coefficients;				// 系数节点
	
	public int getGid() {
		return gid;
	}
	
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public boolean isPoly() {
		return poly;
	}
	
	public void setPoly(boolean poly) {
		this.poly = poly;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String[] getCval() {
		return cval;
	}
	
	public void setCval(String[] cval) {
		this.cval = cval;
	}
	
	public PoundageType getType() {
		return type;
	}
	
	public void setType(PoundageType type) {
		this.type = type;
	}
	
	public Comparison getComparison() {
		return comparison;
	}
	
	public void setComparison(Comparison comparison) {
		this.comparison = comparison;
	}
	
	public Set<Integer> getCoefficients() {
		return coefficients;
	}
	
	public void setCoefficients(Set<Integer> coefficients) {
		this.coefficients = coefficients;
	}

	@Override
	public Integer key() {
		return this.id;
	}
}
