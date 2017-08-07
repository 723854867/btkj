package org.btkj.user.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.common.enums.CRUD_TYPE;
import org.rapid.util.validator.ValidateGroups;

public class AppEditParam extends Param {

	private static final long serialVersionUID = -3857008875210212506L;

	private CRUD_TYPE type;
	@Min(value = 1, groups = {ValidateGroups.UPDATE.class})
	private int appId;
	private int region;
	@NotNull(groups = {ValidateGroups.CREATE.class})
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = {ValidateGroups.CRUD.class})
	private String name;
	@Min(value = 0, groups = {ValidateGroups.CRUD.class})
	private int maxTenantsCount;
	@Min(value = 0, groups = {ValidateGroups.CRUD.class})
	private int maxArticlesCount;
	
	public CRUD_TYPE getType() {
		return type;
	}
	
	public void setType(CRUD_TYPE type) {
		this.type = type;
	}
	
	public int getAppId() {
		return appId;
	}
	
	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxTenantsCount() {
		return maxTenantsCount;
	}

	public void setMaxTenantsCount(int maxTenantsCount) {
		this.maxTenantsCount = maxTenantsCount;
	}

	public int getMaxArticlesCount() {
		return maxArticlesCount;
	}

	public void setMaxArticlesCount(int maxArticlesCount) {
		this.maxArticlesCount = maxArticlesCount;
	}
}
