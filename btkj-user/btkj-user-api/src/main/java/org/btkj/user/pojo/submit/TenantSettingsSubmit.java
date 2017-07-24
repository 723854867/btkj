package org.btkj.user.pojo.submit;

import java.io.Serializable;
import java.util.Set;

public class TenantSettingsSubmit implements Serializable {

	private static final long serialVersionUID = -6167866776479943272L;

	private int tid;
	private String name;
	private Integer teamDepth;
	private Set<Long> nonAutoBind;			
	private Integer bonusScaleCountMod;
	private Integer bonusScaleCountInsuranceMod;
	private Integer bonusScaleRewardMod;
	private Integer bonusScaleRewardInsuranceMod;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Integer getTeamDepth() {
		return teamDepth;
	}

	public void setTeamDepth(Integer teamDepth) {
		this.teamDepth = teamDepth;
	}
	
	public Set<Long> getNonAutoBind() {
		return nonAutoBind;
	}
	
	public void setNonAutoBind(Set<Long> nonAutoBind) {
		this.nonAutoBind = nonAutoBind;
	}

	public Integer getBonusScaleCountMod() {
		return bonusScaleCountMod;
	}

	public void setBonusScaleCountMod(Integer bonusScaleCountMod) {
		this.bonusScaleCountMod = bonusScaleCountMod;
	}
	
	public Integer getBonusScaleCountInsuranceMod() {
		return bonusScaleCountInsuranceMod;
	}
	
	public void setBonusScaleCountInsuranceMod(Integer bonusScaleCountInsuranceMod) {
		this.bonusScaleCountInsuranceMod = bonusScaleCountInsuranceMod;
	}

	public Integer getBonusScaleRewardMod() {
		return bonusScaleRewardMod;
	}

	public void setBonusScaleRewardMod(Integer bonusScaleRewardMod) {
		this.bonusScaleRewardMod = bonusScaleRewardMod;
	}
	
	public Integer getBonusScaleRewardInsuranceMod() {
		return bonusScaleRewardInsuranceMod;
	}
	
	public void setBonusScaleRewardInsuranceMod(Integer bonusScaleRewardInsuranceMod) {
		this.bonusScaleRewardInsuranceMod = bonusScaleRewardInsuranceMod;
	}
}
