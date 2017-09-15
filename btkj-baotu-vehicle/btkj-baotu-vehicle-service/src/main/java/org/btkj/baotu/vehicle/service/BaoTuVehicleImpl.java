package org.btkj.baotu.vehicle.service;

import java.util.Set;

import org.btkj.baotu.vehicle.api.BaoTuVehicle;
import org.btkj.pojo.entity.config.Insurer;
import org.btkj.pojo.info.EmployeeTip;
import org.btkj.pojo.info.VehiclePolicyTips;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("baoTuVehicle")
public class BaoTuVehicleImpl implements BaoTuVehicle {

	@Override
	public Result<Void> order(EmployeeTip employee, Set<Insurer> quote, Set<Insurer> insure, VehiclePolicyTips tips) {
		return Result.result(Code.SYSTEM_ERROR);
	}
}
