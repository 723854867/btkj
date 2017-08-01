package org.btkj.pojo.param.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.btkj.pojo.po.EmployeePO;

public class EmployeeModValidator implements ConstraintValidator<EmployeeMod, Integer> {
	
	@Override
	public void initialize(EmployeeMod mod) {}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value <= 0 ? false : EmployeePO.Mod.check(value);
	}
}
