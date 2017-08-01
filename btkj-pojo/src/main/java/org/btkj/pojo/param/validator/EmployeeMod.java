package org.btkj.pojo.param.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmployeeModValidator.class)
@Documented
public @interface EmployeeMod {

	String message() default "{org.btkj.employee.mod}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
