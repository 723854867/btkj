package org.btkj.vehicle.pojo.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.btkj.pojo.BtkjConsts;
import org.btkj.pojo.enums.CoefficientType;
import org.btkj.pojo.param.EmployeeParam;
import org.rapid.util.math.compare.ComparisonSymbol;
import org.rapid.util.validator.ValidateGroups;

public class PoundageCoefficientEditParam extends EmployeeParam {

	private static final long serialVersionUID = -5457890271653466693L;

	@Min(value = 1, groups = { ValidateGroups.UPDATE.class, ValidateGroups.DELETE.class })
	private int id;
	private int tid;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	@Size(min = BtkjConsts.LIMITS.NAME_MIN, max = BtkjConsts.LIMITS.NAME_MAX, groups = { ValidateGroups.CRUD.class })
	private String name;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private String[] val;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private ComparisonSymbol symbol;
	@NotNull(groups = { ValidateGroups.CREATE.class })
	private CoefficientType coefficientType;
	
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
	
	public ComparisonSymbol getSymbol() {
		return symbol;
	}
	
	public void setSymbol(ComparisonSymbol symbol) {
		this.symbol = symbol;
	}
	
	public CoefficientType getCoefficientType() {
		return coefficientType;
	}
	
	public void setCoefficientType(CoefficientType coefficientType) {
		this.coefficientType = coefficientType;
	}
}
