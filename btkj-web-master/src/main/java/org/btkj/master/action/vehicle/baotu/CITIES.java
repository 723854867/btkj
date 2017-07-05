package org.btkj.master.action.vehicle.baotu;

import java.util.List;

import javax.annotation.Resource;

import org.btkj.pojo.entity.Administrator;
import org.btkj.vehicle.api.VehicleConfigService;
import org.btkj.vehicle.pojo.entity.City;
import org.btkj.web.util.Request;
import org.btkj.web.util.action.AdministratorAction;
import org.rapid.util.common.message.Result;

public class CITIES extends AdministratorAction {
	
	@Resource
	private VehicleConfigService vehicleConfigService;

	@Override
	protected Result<List<City>> execute(Request request, Administrator operator) {
		return Result.result(vehicleConfigService.cities());
	}
}
