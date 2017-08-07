package org.btkj.config.pojo.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.validator.ValidateGroups;

public class ModularEditParam extends Param {

	private static final long serialVersionUID = -8942948934304679356L;

	private CRUD_TYPE type;
	@NotNull(groups = { ValidateGroups.UPDATE.class })
	private String id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	private String parentId;

	public CRUD_TYPE getType() {
		return type;
	}

	public void setType(CRUD_TYPE type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
