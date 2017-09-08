package org.btkj.pojo.param.config;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;
import org.rapid.util.validator.custom.Power;

public class InsurerEditParam extends Param {

	private static final long serialVersionUID = -2982522728695577267L;

	@NotNull(groups = { ValidateGroups.CRUD.class })
	@Power(groups = { ValidateGroups.CRUD.class })
	private Integer id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String icon;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	@Power(groups = { ValidateGroups.CRUD.class })
	private Integer biHuId;
	private String leBaoBaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getBiHuId() {
		return biHuId;
	}

	public void setBiHuId(Integer biHuId) {
		this.biHuId = biHuId;
	}

	public String getLeBaoBaId() {
		return leBaoBaId;
	}

	public void setLeBaoBaId(String leBaoBaId) {
		this.leBaoBaId = leBaoBaId;
	}
}
