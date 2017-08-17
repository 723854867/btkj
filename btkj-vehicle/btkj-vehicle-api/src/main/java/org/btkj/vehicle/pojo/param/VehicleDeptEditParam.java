package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;

public class VehicleDeptEditParam extends Param {

	private static final long serialVersionUID = -7153580670550110341L;

	@NotNull(groups = { ValidateGroups.UPDATE.class })
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	private Integer id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	private Integer brandId;
	@NotNull(groups = { ValidateGroups.CRUD.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
