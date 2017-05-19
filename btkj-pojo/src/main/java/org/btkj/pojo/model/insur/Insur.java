package org.btkj.pojo.model.insur;

import java.io.Serializable;

/**
 * 第三者责任险
 * 
 * @author ahab
 */
public class Insur implements Serializable{

	private static final long serialVersionUID = -2156138426820177782L;

	private Integer coverage;
	private Boolean fullClaim;
	
	public Integer getCoverage() {
		return coverage;
	}
	
	public void setCoverage(Integer coverage) {
		this.coverage = coverage;
	}
	
	public Boolean isFullClaim() {
		return fullClaim;
	}
	
	public void setFullClaim(Boolean fullClaim) {
		this.fullClaim = fullClaim;
	}
}
