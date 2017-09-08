package org.btkj.pojo.param.vehicle;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.enums.BonusManageConfigType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.validator.ValidateGroups;

public class BonusManageConfigEditParam extends EmployeeParam {

	private static final long serialVersionUID = -437750523425543983L;

	@NotNull(groups = {ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class})
	private String id;
	@NotNull(groups = {ValidateGroups.CREATE.class})
	@Min(value = 0, groups = {ValidateGroups.CRUD.class})
	@Max(value = 500, groups = {ValidateGroups.CRUD.class})
	private Integer rate;
	@NotNull(groups = {ValidateGroups.CREATE.class})
	@Min(value = 1, groups = {ValidateGroups.CRUD.class})
	private Integer teamDepth;
	@NotNull(groups = {ValidateGroups.CREATE.class})
	private BonusManageConfigType configType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getTeamDepth() {
		return teamDepth;
	}

	public void setTeamDepth(Integer teamDepth) {
		this.teamDepth = teamDepth;
	}

	public BonusManageConfigType getConfigType() {
		return configType;
	}

	public void setConfigType(BonusManageConfigType configType) {
		this.configType = configType;
	}
}
