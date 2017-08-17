package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.rapid.util.validator.ValidateGroups;

public class BonusScaleConfigEditParam extends EmployeeParam {

	private static final long serialVersionUID = 625155037429352832L;

	@NotNull(groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	@Min(value = 1, groups = { ValidateGroups.CREATE.class })
	private Integer id;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Min(value = 0, groups = { ValidateGroups.CREATE.class })
	@Max(value = 500, groups = { ValidateGroups.CREATE.class })
	private Integer rate;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private ComparisonSymbol symbol;
	private String[] val;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public ComparisonSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(ComparisonSymbol symbol) {
		this.symbol = symbol;
	}

	public String[] getVal() {
		return val;
	}

	public void setVal(String[] val) {
		this.val = val;
	}
}
