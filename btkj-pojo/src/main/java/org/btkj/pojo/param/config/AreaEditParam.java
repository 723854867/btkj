package org.btkj.pojo.param.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.Param;
import org.rapid.util.validator.ValidateGroups;

public class AreaEditParam extends Param {

	private static final long serialVersionUID = -5926407207895626938L;

	@NotNull(groups = { ValidateGroups.CRUD.class })
	private Integer code;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private Integer biHuId;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private Boolean priceNoTax;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = BtkjConsts.LIMITS.MIN_RENEWAL_PERIOD, groups = { ValidateGroups.CRUD.class })
	@Max(value = BtkjConsts.LIMITS.MAX_RENEWAL_PERIOD, groups = { ValidateGroups.CRUD.class })
	private Integer renewalPeriod;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getBiHuId() {
		return biHuId;
	}

	public void setBiHuId(Integer biHuId) {
		this.biHuId = biHuId;
	}

	public Boolean getPriceNoTax() {
		return priceNoTax;
	}
	
	public void setPriceNoTax(Boolean priceNoTax) {
		this.priceNoTax = priceNoTax;
	}

	public Integer getRenewalPeriod() {
		return renewalPeriod;
	}

	public void setRenewalPeriod(Integer renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}
}
