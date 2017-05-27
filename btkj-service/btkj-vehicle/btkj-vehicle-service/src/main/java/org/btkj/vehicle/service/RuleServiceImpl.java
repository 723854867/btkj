package org.btkj.vehicle.service;

import org.btkj.pojo.model.EmployeeForm;
import org.btkj.pojo.submit.VehicleOrderSubmit;
import org.btkj.vehicle.api.RuleService;
import org.springframework.stereotype.Service;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {

	@Override
	public boolean check(EmployeeForm form, VehicleOrderSubmit submit) {
		return true;
	}
}
