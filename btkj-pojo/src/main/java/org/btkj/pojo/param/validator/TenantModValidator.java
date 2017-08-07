package org.btkj.pojo.param.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.btkj.pojo.po.TenantPO;

public class TenantModValidator implements ConstraintValidator<EmployeeMod, Integer> {

	@Override
	public void initialize(EmployeeMod constraintAnnotation) {}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (null == value)
			return false;
		return value <= 0 ? false : TenantPO.Mod.check(value);
	}
}
