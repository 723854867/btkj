package org.btkj.lebaoba.vehicle.service;

import org.btkj.lebaoba.vehicle.api.LeBaoBaVehicle;
import org.btkj.pojo.entity.Renewal;
import org.btkj.pojo.model.EmployeeForm;
import org.rapid.util.common.consts.code.Code;
import org.rapid.util.common.message.Result;
import org.springframework.stereotype.Service;

@Service("leBaoBaVehicle")
public class LeBaoBaVehicleImpl implements LeBaoBaVehicle {

	@Override
	public Result<Void> order(EmployeeForm employeeForm, int quoteMod, int insureMod, Renewal renewal) {
		return Result.result(Code.SYSTEM_ERROR);
	}
}
