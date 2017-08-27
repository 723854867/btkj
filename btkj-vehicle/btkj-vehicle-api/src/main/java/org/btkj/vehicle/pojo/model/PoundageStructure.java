package org.btkj.vehicle.pojo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.btkj.vehicle.pojo.enums.PoundageNodeType;

public class PoundageStructure implements Serializable {

	private static final long serialVersionUID = -1646823747310649612L;

	private int id;													// 节点ID
	private String name;											// 节点名
	private PoundageNodeType type;
	private Map<Integer, PoundageStructure> children;				// 子节点
	private CoefficientStructure coefficientStructure;				// 可绑定系数的结构类型
	
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
	
	public PoundageNodeType getType() {
		return type;
	}
	
	public void setType(PoundageNodeType type) {
		this.type = type;
	}
	
	public Map<Integer, PoundageStructure> getChildren() {
		return children;
	}
	
	public void setChildren(Map<Integer, PoundageStructure> children) {
		this.children = children;
	}
	
	public CoefficientStructure getCoefficientStructure() {
		return coefficientStructure;
	}
	
	public void setCoefficientStructure(CoefficientStructure coefficientStructure) {
		this.coefficientStructure = coefficientStructure;
	}
	
	public void addChild(PoundageStructure structure) {
		if (null == this.children)
			this.children = new HashMap<Integer, PoundageStructure>();
		this.children.put(structure.getId(), structure);
	}
}
