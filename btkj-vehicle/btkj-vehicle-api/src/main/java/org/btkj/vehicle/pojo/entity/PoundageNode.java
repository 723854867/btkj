package org.btkj.vehicle.pojo.entity;

import java.util.LinkedList;
import java.util.Set;

import org.btkj.vehicle.pojo.enums.PoundageType;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.math.tree.NodeImpl;

public class PoundageNode extends NodeImpl<Integer> implements UniqueModel<Integer> {

	private static final long serialVersionUID = 4034992659956349660L;

	private PoundageType type;
	private LinkedList<Integer> path;
	private Set<Integer> coefficients;				// 系数节点
	
	public PoundageType getType() {
		return type;
	}
	
	public void setType(PoundageType type) {
		this.type = type;
	}
	
	public LinkedList<Integer> getPath() {
		return path;
	}
	
	public void setPath(LinkedList<Integer> path) {
		this.path = path;
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
