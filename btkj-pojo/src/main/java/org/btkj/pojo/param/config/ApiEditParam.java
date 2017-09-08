package org.btkj.pojo.param.config;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.custom.ClassName;

public class ApiEditParam extends Param {

	private static final long serialVersionUID = -8942948934304679356L;

	@Min(value = 1, groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	private int id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@ClassName(groups = { ValidateGroups.CRUD.class })
	private String pkg;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = 1, groups = { ValidateGroups.CRUD.class })
	private Integer modularId;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getModularId() {
		return modularId;
	}

	public void setModularId(Integer modularId) {
		this.modularId = modularId;
	}
}
