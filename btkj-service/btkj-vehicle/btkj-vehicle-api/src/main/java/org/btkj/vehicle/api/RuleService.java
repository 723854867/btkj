package org.btkj.vehicle.api;

import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.submit.VehicleOrderSubmit;

public interface RuleService {

	/**
	 * 检测规则
	 * 
	 * @param form
	 * @param submit
	 * @return
	 */
	boolean check(EmployeeForm form, VehicleOrderSubmit submit);
}
