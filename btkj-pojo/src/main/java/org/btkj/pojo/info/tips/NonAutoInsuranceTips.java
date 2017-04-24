package org.btkj.pojo.info.tips;

import java.io.Serializable;

import org.btkj.pojo.entity.NonAutoInsurance;

/**
 * 非车险首页列表信息
 * 
 * @author ahab
 *
 */
public class NonAutoInsuranceTips implements Serializable {

	private static final long serialVersionUID = 1732635911364746139L;

	private int type;
	private String name; 				// 险种名
	
	public NonAutoInsuranceTips() {}
	
	public NonAutoInsuranceTips(NonAutoInsurance insurance) {
		this.type = insurance.getType();
		this.name = insurance.getName();
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
