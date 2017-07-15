package org.btkj.user.pojo.submit;

import java.io.Serializable;
import java.util.Set;

public class TenantSettingsSubmit implements Serializable {

	private static final long serialVersionUID = -6167866776479943272L;

	private int tid;
	private Integer teamDepth;
	private Set<Long> nonAutoBind;			
	private Integer bonusScaleCountMod;
	private Integer bonusScaleRewardMod;

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

	public Integer getBonusScaleRewardMod() {
		return bonusScaleRewardMod;
	}

	public void setBonusScaleRewardMod(Integer bonusScaleRewardMod) {
		this.bonusScaleRewardMod = bonusScaleRewardMod;
	}
}