package org.btkj.master.action.vehicle.baotu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.vehicle.api.VehicleManageService;
import org.btkj.vehicle.pojo.entity.Area;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class AREAS extends AdministratorAction {
	
	@Resource
	private VehicleManageService vehicleManageService;

	@Override
	protected Result<List<Area>> execute(Request request, Administrator operator) {
		return Result.result(vehicleManageService.cities());
	}
}
