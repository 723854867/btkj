package org.btkj.config.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.ModularType;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.validator.ValidateGroups;

public class ModularEditParam extends Param {

	private static final long serialVersionUID = -8942948934304679356L;

	private CRUD_TYPE type;
	@Min(value = 1, groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	private int id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	private int parentId;
	@NotNull(groups = {ValidateGroups.CREATE.class})
	private ModularType modularType;				// 该模块适用的目标类型

	public CRUD_TYPE getType() {
		return type;
	}

	public void setType(CRUD_TYPE type) {
		this.type = type;
	}

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

	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public ModularType getModularType() {
		return modularType;
	}
	
	public void setModularType(ModularType modularType) {
		this.modularType = modularType;
	}
}