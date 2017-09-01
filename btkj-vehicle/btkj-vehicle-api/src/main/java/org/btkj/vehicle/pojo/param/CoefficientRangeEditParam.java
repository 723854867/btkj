package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.math.compare.Comparison;
import org.rapid.util.validator.ValidateGroups;

public class CoefficientRangeEditParam extends EmployeeParam {

	private static final long serialVersionUID = -8368289058120488350L;

	@Min(value = 1, groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	private int id;
	private int tid;
	private int coefficientId;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String[] val;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private Comparison symbol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getCoefficientId() {
		return coefficientId;
	}
	
	public void setCoefficientId(int coefficientId) {
		this.coefficientId = coefficientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getVal() {
		return val;
	}

	public void setVal(String[] val) {
		this.val = val;
	}

	public Comparison getSymbol() {
		return symbol;
	}

	public void setSymbol(Comparison symbol) {
		this.symbol = symbol;
	}
}
