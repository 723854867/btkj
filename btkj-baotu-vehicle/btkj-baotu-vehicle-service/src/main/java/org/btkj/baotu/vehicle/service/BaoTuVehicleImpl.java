package org.btkj.baotu.vehicle.service;

import java.util.Set;

import org.btkj.baotu.vehicle.api.BaoTuVehicle;
import org.btkj.pojo.info.tips.VehiclePolicyTips;
import org.btkj.pojo.model.EmployeeForm;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("baoTuVehicle")
public class BaoTuVehicleImpl implements BaoTuVehicle {

	@Override
	public Result<Void> order(EmployeeForm employeeForm, Set<Integer> quote, Set<Integer> insure, VehiclePolicyTips tips) {
		return Result.result(Code.SYSTEM_ERROR);
	}
}
