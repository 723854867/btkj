package org.btkj.user.pojo.param;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.EmployeeParam;
import org.btkj.pojo.param.validator.TenantMod;

public class TenantSetParam extends EmployeeParam {

	private static final long serialVersionUID = -8648825237034153936L;

	@TenantMod
	private Integer mod;
	@Min(1)
	@Max(10)
	private Integer teamDepth;
	@Size(min = BtkjConsts.LIMITS.PHONE_MIN, max = BtkjConsts.LIMITS.PHONE_MAX)
	private String servicePhone;
	@Size(max = BtkjConsts.LIMITS.NONAUTO_MAX)
	private Set<Long> nonAutoBind;

	public Integer getMod() {
		return mod;
	}

	public void setMod(Integer mod) {
		this.mod = mod;
	}

	public Integer getTeamDepth() {
		return teamDepth;
	}

	public void setTeamDepth(Integer teamDepth) {
		this.teamDepth = teamDepth;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public Set<Long> getNonAutoBind() {
		return nonAutoBind;
	}

	public void setNonAutoBind(Set<Long> nonAutoBind) {
		this.nonAutoBind = nonAutoBind;
	}
}
